package com.miiky.shape.models

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
	val titleResource: Int,
	val route: String,
	val icon: ImageVector,
	val iconAlt: ImageVector,
)
