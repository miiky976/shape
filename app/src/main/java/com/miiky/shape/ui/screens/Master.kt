package com.miiky.shape.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.Workspaces
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material.icons.rounded.Workspaces
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.miiky.shape.ui.navigator.ShapeNavItems
import com.miiky.shape.ui.navigator.ShapesNavigator
import kotlinx.coroutines.launch

// This function is supposed to handle all the main UI
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Master(modifier: Modifier = Modifier) {
	val scope = rememberCoroutineScope()

	val navControl = rememberNavController()
	val backStackEntry = navControl.currentBackStackEntryAsState()

	val drawer = rememberDrawerState(initialValue = DrawerValue.Closed)

	ModalNavigationDrawer(
		drawerState = drawer,
		drawerContent = {
			ModalDrawerSheet {
				ShapeNavItems.forEach {
					val selected = it.route == backStackEntry.value?.destination?.route
					NavigationDrawerItem(
						label = { Text(text = stringResource(id = it.titleResource)) },
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