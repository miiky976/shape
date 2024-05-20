package com.miiky.shape.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.miiky.shape.R

@Composable
fun NumberTextInput(
	modifier: Modifier = Modifier,
	number: MutableIntState,
	steps: Int = 1,
	label: String
) {
	OutlinedTextField(
		value = number.intValue.toString(),
		onValueChange = {
			if (it.isNotEmpty()) {
				number.intValue = it.toInt()
				return@OutlinedTextField
			} else {
				number.intValue = 0
			}
		},
		keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
		label = { Text(text = label) },
		trailingIcon = {
			IconButton(onClick = { number.intValue += steps}) {
				Icon(Icons.Rounded.Add, contentDescription = stringResource(id = R.string.add))
			}
		},
		leadingIcon = {
			IconButton(onClick = { number.intValue -= steps}) {
				Icon(
					Icons.Rounded.Remove,
					contentDescription = stringResource(id = R.string.subtract)
				)
			}
		},
		modifier = modifier
	)
}