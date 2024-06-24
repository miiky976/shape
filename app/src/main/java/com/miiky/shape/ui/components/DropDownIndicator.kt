package com.miiky.shape.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import com.miiky.shape.R

@Composable
fun DropDownIndicator(
	modifier: Modifier = Modifier,
	hidden: Boolean
) {
	val rotation = animateFloatAsState(targetValue = if (hidden) 0f else 180f, label = "")
	Icon(
		imageVector = Icons.Rounded.ArrowDropDown,
		contentDescription = stringResource(id = R.string.hide),
		modifier = modifier.rotate(rotation.value)
	)
}