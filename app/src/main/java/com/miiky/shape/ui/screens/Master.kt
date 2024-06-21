package com.miiky.shape.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.Workspaces
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material.icons.rounded.Commit
import androidx.compose.material.icons.rounded.RadioButtonChecked
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material.icons.rounded.Workspaces
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miiky.shape.R
import com.miiky.shape.models.NavItem
import com.miiky.shape.ui.navigator.ShapesNavigator
import kotlinx.coroutines.launch

// This function is supposed to handle all the main UI
@Composable
fun Master(modifier: Modifier = Modifier) {
	val scope = rememberCoroutineScope()

	val navControl = rememberNavController()
	val backStackEntry = navControl.currentBackStackEntryAsState()

	val drawer = rememberDrawerState(initialValue = DrawerValue.Closed)

	val navItems = listOf(
		NavItem(
			stringResource(id = R.string.circle),
			"circle",
			Icons.Outlined.Circle,
			Icons.Rounded.Circle
		),
		NavItem(
			stringResource(id = R.string.line),
			"line",
			Icons.Outlined.Workspaces,
			Icons.Rounded.Workspaces
		)
	)

	ModalNavigationDrawer(
		drawerState = drawer,
		drawerContent = {
			ModalDrawerSheet {
				navItems.forEach {
					val selected = it.route == backStackEntry.value?.destination?.route
					NavigationDrawerItem(
						label = { Text(text = it.title) },
						selected = selected,
						onClick = {
							scope.launch {
								drawer.close()
								navControl.navigate(it.route)
							}
						},
						modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
						icon = {
							Icon(
								imageVector = if (!selected) it.icon else it.iconAlt,
								contentDescription = null
							)
						}
					)
				}
			}
		},
		modifier = modifier.fillMaxSize()
	) {
		ShapesNavigator(navControl = navControl)
	}
}