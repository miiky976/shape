package com.miiky.shape.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowDropUp
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.material.icons.rounded.ArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.miiky.shape.R
import com.miiky.shape.ui.components.NumberTextInput
import com.miiky.shape.ui.components.ShapeCanvas
import com.miiky.shape.methods.line
import com.miiky.shape.models.Cords

@Composable
fun LineScreen(modifier: Modifier = Modifier) {
	val hidden = remember { mutableStateOf(true) }

	// Cords stuff
	val startX = remember { mutableIntStateOf(-10) }
	val startY = remember { mutableIntStateOf(-10) }
	val endX = remember { mutableIntStateOf(20) }
	val endY = remember { mutableIntStateOf(-9) }
	var list = remember {
		line(Cords(startX.intValue, startY.intValue), Cords(endX.intValue, endY.intValue))
	}
	val selected = remember { mutableIntStateOf(0) }
	fun update() {
		selected.intValue = list.size -1
		list.clear()
		list = line(Cords(startX.intValue, startY.intValue), Cords(endX.intValue, endY.intValue))
		selected.intValue = 0
	}
	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(10.dp)
	) {
		Row {
			NumberTextInput(number = startX, label = "start X", modifier = Modifier.weight(1f))
			NumberTextInput(number = startY, label = "start Y", modifier = Modifier.weight(1f))
		}
		Row {
			NumberTextInput(number = endX, label = "end X", modifier = Modifier.weight(1f))
			NumberTextInput(number = endY, label = "end Y", modifier = Modifier.weight(1f))
		}
		Row {
			Button(onClick = {
				update()
			}) {
				Text(text = "Force update")
			}
		}
		ElevatedCard(
			modifier = Modifier
		) {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.clickable {
						hidden.value = !hidden.value
					}
			) {
				Column(
					modifier = Modifier
						.weight(1f)
						.align(Alignment.CenterVertically)
						.padding(24.dp)
				) {
					Text(
						text = stringResource(id = R.string.blocks) + ": " + list.size,
					)
					Text(
						style = MaterialTheme.typography.headlineMedium,
						text = "X: ${list[selected.intValue].x} Y: ${list[selected.intValue].y}"
					)
				}
				Icon(
					if (!hidden.value) Icons.Rounded.ArrowDropDown else Icons.Rounded.ArrowDropUp, contentDescription = null,
					modifier = Modifier
						.align(Alignment.CenterVertically)
						.padding(24.dp)
				)
			}
			AnimatedVisibility(hidden.value, modifier = Modifier.weight(1f, fill = false)) {
				ShapeCanvas(
					cords = list,
					selected = list[selected.intValue]
				)
			}
			Row(
				modifier = Modifier.padding(10.dp)
			) {
				FilledIconButton(onClick = {
					selected.intValue -= 1
					if (selected.intValue < 0) {
						selected.intValue = 0
					}
				}) {
					Icon(
						Icons.Rounded.ArrowLeft,
						contentDescription = stringResource(id = R.string.prevItem)
					)
				}
				Slider(
					value = selected.intValue.toFloat(),
					onValueChange = { selected.intValue = it.toInt() },
					valueRange = (0f..(list.size - 1).toFloat()),
					modifier = Modifier.weight(1f)
				)
				FilledIconButton(onClick = {
					selected.intValue += 1
					if (selected.intValue > list.size - 1) {
						selected.intValue = list.size - 1
					}
				}) {
					Icon(
						Icons.Rounded.ArrowRight,
						contentDescription = stringResource(id = R.string.nextItem)
					)
				}
			}
		}
	}

}