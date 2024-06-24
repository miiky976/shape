package com.miiky.shape.ui.navigator

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.Workspaces
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material.icons.rounded.Workspaces
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.miiky.shape.R
import com.miiky.shape.models.NavItem
import com.miiky.shape.ui.screens.CircleScreen
import com.miiky.shape.ui.screens.LineScreen
import com.miiky.shape.ui.screens.LineScreenExp

@Composable
fun ShapesNavigator(
	modifier: Modifier = Modifier,
	navControl: NavHostController
) {
	NavHost(navController = navControl, startDestination = "lineexp", modifier = modifier) {
		composable("line") {
			LineScreen()
		}
		composable("circle") {
			CircleScreen()
		}
		composable("lineexp") {
			LineScreenExp()
		}
	}
}

val ShapeNavItems = listOf(
	NavItem(
		R.string.circle,
		"circle",
		Icons.Outlined.Circle,
		Icons.Rounded.Circle
	),
	NavItem(
		R.string.line,
		"line",
		Icons.Outlined.Workspaces,
		Icons.Rounded.Workspaces
	),
	NavItem(
		R.string.line,
		"lineexp",
		Icons.Outlined.Workspaces,
		Icons.Rounded.Workspaces
	),
)
