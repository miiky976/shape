package com.miiky.shape.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowLeft
import androidx.compose.material.icons.automirrored.rounded.ArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miiky.shape.R
import com.miiky.shape.models.Cords

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CordsInput(
	modifier: Modifier = Modifier,
	steps: Int = 1,
	cord: MutableState<Cords> = mutableStateOf(Cords(0, 0)),
	label: String = "Cords"
) {
	// these are only for the persistent view :) called recomposed stuff
	val x = remember { mutableIntStateOf(cord.value.x) }
	val y = remember { mutableIntStateOf(cord.value.y) }

	val hidden = remember { mutableStateOf(false) }
	val size = remember { mutableStateOf(10.sp) }

	val textSize by animateDpAsState(targetValue = if (!hidden.value) 20.dp else 30.dp, label = "")

	if (hidden.value) {
		size.value = 24.sp
	} else {
		size.value = 10.sp
	}

	val sep = 10.dp
	OutlinedCard(
		modifier
	) {
		Box(
			modifier = Modifier.clickable {
				hidden.value = !hidden.value
			}
		) {
			Column {
				Row(
					modifier = Modifier,
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.SpaceBetween
				) {
					Text(
						text = label, modifier = Modifier
							.padding(sep).weight(1f),
						fontSize = textSize.value.sp
					)
					AnimatedVisibility(
						visible = hidden.value,
						modifier = Modifier.weight(1f),
					) {
						Text(text = "X: ${x.intValue} Y: ${y.intValue}")
					}
					DropDownIndicator(hidden = hidden.value, modifier = Modifier.padding(sep))
				}
				AnimatedVisibility(visible = !hidden.value) {
					Row(
						modifier = Modifier.padding(start = sep, end = sep, bottom = sep),
						Arrangement.spacedBy(sep)
					) {
						Column(
							modifier = modifier.weight(1f)
						) {
							Row {
								Text(text = "X")
								BasicTextField(
									value = x.intValue.toString(),
									onValueChange = {
										try {
											x.intValue = it.toInt()
											cord.value.x = it.toInt()
										} catch (_: Exception) {
										}
									},
									modifier = Modifier
										.weight(1f)
										.padding(start = sep),
									singleLine = true,
									keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
									textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface),
									cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
								)
							}
							SingleChoiceSegmentedButtonRow(
								modifier = Modifier.fillMaxWidth()
							) {
								SegmentedButton(
									selected = false,
									onClick = {
										x.intValue -= steps
										cord.value.x -= steps
									},
									shape = SegmentedButtonDefaults.baseShape.copy(
										bottomEnd = CornerSize(
											0.dp
										), topEnd = CornerSize(0.dp)
									)
								) {
									Icon(
										imageVector = Icons.AutoMirrored.Rounded.ArrowLeft,
										contentDescription = stringResource(
											id = R.string.subtract
										)
									)
								}
								SegmentedButton(
									selected = false,
									onClick = {
										x.intValue += steps
										cord.value.x += steps
									},
									shape = SegmentedButtonDefaults.baseShape.copy(
										topStart = CornerSize(
											0.dp
										), bottomStart = CornerSize(0.dp)
									)
								) {
									Icon(
										imageVector = Icons.AutoMirrored.Rounded.ArrowRight,
										contentDescription = stringResource(
											id = R.string.add
										)
									)
								}
							}
						}
						Column(
							modifier = Modifier.weight(1f)
						) {
							Row {
								Text(text = "Y")
								BasicTextField(
									value = y.intValue.toString(),
									onValueChange = {
										try {
											y.intValue = it.toInt()
											cord.value.y = it.toInt()
										} catch (_: Exception) {
										}
									},
									modifier = Modifier
										.weight(1f)
										.padding(start = sep),
									singleLine = true,
									keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
									textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface),
									cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
								)
							}
							SingleChoiceSegmentedButtonRow(
								modifier = Modifier.fillMaxWidth()
							) {
								SegmentedButton(
									selected = false,
									onClick = {
										y.intValue -= steps
										cord.value.y -= steps
									},
									shape = SegmentedButtonDefaults.baseShape.copy(
										bottomEnd = CornerSize(
											0.dp
										), topEnd = CornerSize(0.dp)
									)
								) {
									Icon(
										imageVector = Icons.AutoMirrored.Rounded.ArrowLeft,
										contentDescription = stringResource(
											id = R.string.subtract
										)
									)
								}
								SegmentedButton(
									selected = false,
									onClick = {
										y.intValue += steps
										cord.value.y += steps
									},
									shape = SegmentedButtonDefaults.baseShape.copy(
										topStart = CornerSize(
											0.dp
										), bottomStart = CornerSize(0.dp)
									)
								) {
									Icon(
										imageVector = Icons.AutoMirrored.Rounded.ArrowRight,
										contentDescription = stringResource(
											id = R.string.add
										)
									)
								}
							}
						}
					}
				}
			}
		}
	}
}