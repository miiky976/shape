// @formatter:on
package com.miiky.shape.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FitScreen
import androidx.compose.material.icons.rounded.SearchOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.miiky.shape.R
import com.miiky.shape.models.Cords
import kotlin.math.sqrt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShapeCanvas(
	modifier: Modifier = Modifier,
	cords: List<Cords>,
	selected: Cords
) {
	val xMax = cords.maxOf { it.x }
	val xMin = cords.minOf { it.x }
	val xRange = (xMin - 1..xMax + 2)

	val yMax = cords.maxOf { it.y }
	val yMin = cords.minOf { it.y }
	val yRange = (yMin - 1..yMax + 2)

	val zoom = remember { mutableFloatStateOf(1f) }
	val offset = remember { mutableStateOf(Offset.Zero) }

	val primary = MaterialTheme.colorScheme.primary
	val outline = MaterialTheme.colorScheme.surfaceVariant
	val transparent = Color.Transparent

	val lineColors = listOf(outline, transparent)
	val lineColor = remember { mutableStateOf(lineColors[0]) }
	if (zoom.floatValue <= 1f) {
		lineColor.value = lineColors[1]
	} else {
		lineColor.value = lineColors[0]
	}

	Surface(
		modifier = modifier,
		shape = RoundedCornerShape(8.dp),
		tonalElevation = 4.dp
	) {
		Box(
			modifier = Modifier.fillMaxSize()
		) {
			val state = rememberTransformableState { scale, pan, _ ->
				zoom.floatValue =
					(zoom.floatValue * scale).coerceIn(0.6f, sqrt(xRange.count().toFloat()) * 2)
				offset.value += pan / zoom.floatValue
			}
			Canvas(
				modifier = Modifier
					.fillMaxSize()
					.transformable(state),
			) {

				val ws = if (xRange.count() >= yRange.count()) { size.width / xRange.count() } else { size.height / yRange.count() }
				val xHalf = size.width / 2f
				val yHalf = size.height / 2f
				scale(zoom.floatValue) {
					translate(
						left = -xRange.elementAt(xRange.count() / 2 - 1) * ws + xHalf + offset.value.x,
						top = -yRange.elementAt(yRange.count() / 2 - 1) * ws + yHalf + offset.value.y
					) {
						// draw the lines
						xRange.forEach {
							drawLine(
								color = lineColor.value,
								start = Offset(x = it * ws, y = yMin * ws - ws),
								end = Offset(x = it * ws, y = yMax * ws + 2 * ws)
							)
						}
						yRange.forEach {
							drawLine(
								color = lineColor.value,
								start = Offset(x = xMin * ws - ws, y = it * ws),
								end = Offset(x = xMax * ws + 2 * ws, y = it * ws)
							)
						}
						// Draw the cords
						cords.forEach {
							drawRect(
								color = outline,
								size = Size(ws, ws),
								topLeft = Offset(
									x = it.x * ws,
									y = it.y * ws
								)
							)
						}
						// Draw Selected
						drawRect(
							color = primary,
							size = Size(ws, ws),
							topLeft = Offset(
								x = selected.x * ws,
								y = selected.y * ws
							)
						)
					}
				}
			}
			IconButton(
				onClick = {
					zoom.floatValue = 1f
				},
				modifier = Modifier.align(Alignment.BottomEnd)
			) {
				Icon(
					Icons.Rounded.SearchOff,
					contentDescription = stringResource(id = R.string.resetZoom)
				)
			}
			IconButton(
				onClick = {
					offset.value = Offset.Zero
				},
				modifier = Modifier.align(Alignment.BottomStart)
			) {
				Icon(
					Icons.Rounded.FitScreen,
					contentDescription = stringResource(id = R.string.fitScreen)
				)
			}
		}
	}
}