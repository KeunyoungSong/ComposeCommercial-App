package com.keunyoung.composecommercial_app.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.keunyoung.composecommercial_app.model.MainNavigationItem
import com.keunyoung.composecommercial_app.ui.theme.ComposeCommercialAppTheme

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	ComposeCommercialAppTheme {
		MainScreen()
	}
}

@Composable
fun MainScreen() {
	val navHostController = rememberNavController()
	
	Scaffold(bottomBar = {
		MainBottomNavigationBar(navHostController)
	}) { innerPadding ->
		MainNavigationScreen(navController = navHostController, innerPadding)
	}
}

@Composable
fun MainBottomNavigationBar(navController: NavController) {
	val bottomNavigationItems = listOf(
		MainNavigationItem.Main,
		MainNavigationItem.Category,
		MainNavigationItem.MyPage,
	)
	
	BottomAppBar(
		containerColor = MaterialTheme.colorScheme.primaryContainer,
		contentColor = MaterialTheme.colorScheme.primary
	) {
		val navBackStackEntry by navController.currentBackStackEntryAsState()
		val currentRoute = navBackStackEntry?.destination?.route
		
		bottomNavigationItems.forEach { navItem ->
			NavigationBarItem(selected = currentRoute == navItem.route, icon = {
				Icon(navItem.icon, navItem.route)
			}, onClick = {
				navController.navigate(navItem.route) {
					popUpTo(navController.graph.findStartDestination().id) {
						saveState = true
					}
					launchSingleTop = true
					restoreState = true
				}
			})
		}
	}
}

@Composable
fun MainNavigationScreen(navController: NavHostController, innerPadding: PaddingValues) {
	NavHost(
		modifier = Modifier.padding(innerPadding),
		navController = navController,
		startDestination = MainNavigationItem.Main.route,
	) {
		composable(MainNavigationItem.Main.route) {
			Text("Main")
		}
		composable(MainNavigationItem.Category.route) {
			Text("Category")
		}
		composable(MainNavigationItem.MyPage.route) {
			Text("MyPage")
		}
	}
}

