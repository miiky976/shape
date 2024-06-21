package com.miiky.shape.ui.navigator

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.miiky.shape.R
import com.miiky.shape.models.NavItem
import com.miiky.shape.ui.screens.CircleScreen
import com.miiky.shape.ui.screens.LineScreen

@Composable
fun ShapesNavigator(
	modifier: Modifier = Modifier,
	navControl: NavHostController
) {
	NavHost(navController = navControl, startDestination = "line") {
		composable("line") {
			LineScreen()
		}
		composable("circle") {
			CircleScreen()
		}
	}
}