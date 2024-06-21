package com.miiky.shape.models

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
	val title: String,
	val route: String,
	val icon: ImageVector,
	val iconAlt: ImageVector,
)
