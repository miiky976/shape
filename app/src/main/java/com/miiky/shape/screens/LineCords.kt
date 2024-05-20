package com.miiky.shape.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.material.icons.rounded.ArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
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
import com.miiky.shape.components.NumberTextInput
import com.miiky.shape.components.ShapeCanvas
import com.miiky.shape.methods.Line
import com.miiky.shape.models.Cords

@Composable
fun LineCords(modifier: Modifier = Modifier) {
	val startX = remember { mutableIntStateOf(-5) }
	val startY = remember { mutableIntStateOf(-10) }
	val endX = remember { mutableIntStateOf(8) }
	val endY = remember { mutableIntStateOf(-2) }
	val start = remember {
		mutableStateOf(Cords(startX.intValue, startY.intValue))
	}
	val end = remember {
		mutableStateOf(Cords(endX.intValue, endY.intValue))
	}
	var list = remember {
		Line(start.value, end.value)
	}
	val hidden = remember { mutableStateOf(true) }
	val selected = remember { mutableIntStateOf(0) }
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
			Switch(checked = hidden.value, onCheckedChange = { hidden.value = it })
			Button(onClick = {
				list = Line(start.value, end.value)
			}) {
				Text(text = "Force update")
			}
		}
		ElevatedCard(
			modifier = if (hidden.value) {Modifier.weight(1f)}else {Modifier.fillMaxWidth()}
		) {
			if (hidden.value) {
				ShapeCanvas(
					modifier = Modifier.weight(1f),
					cords = list,
					selected = list[selected.intValue]
				)
			}
			Text(text = list[selected.intValue].toString(), modifier = Modifier.padding(24.dp).align(Alignment.CenterHorizontally))
		}
		Row(
			modifier = Modifier.align(Alignment.End)
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