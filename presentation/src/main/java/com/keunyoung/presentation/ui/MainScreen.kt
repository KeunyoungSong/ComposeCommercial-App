package com.keunyoung.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.keunyoung.presentation.model.MainNavigationItem
import com.keunyoung.presentation.ui.theme.ComposeCommercialAppTheme
import com.keunyoung.presentation.viewmodel.MainViewModel

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	ComposeCommercialAppTheme {
		MainScreen()
	}
}

@Composable
fun MainScreen() {
	val viewModel = hiltViewModel<MainViewModel>()
	val navHostController = rememberNavController()
	
	Scaffold(topBar = {
		TopAppBar(viewModel)
	}, bottomBar = {
		BottomAppBar(navHostController)
	}) { innerPadding ->
		MainScreen(navController = navHostController, innerPadding)
	}
}

@Composable
fun BottomAppBar(navController: NavController) {
	val bottomNavigationItems = listOf(
		MainNavigationItem.Main,
		MainNavigationItem.Category,
		MainNavigationItem.MyPage,
	)
	
	BottomAppBar(
		containerColor = MaterialTheme.colorScheme.primaryContainer, contentColor = MaterialTheme.colorScheme.primary
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
fun MainScreen(navController: NavHostController, innerPadding: PaddingValues) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(viewModel: MainViewModel) {
	TopAppBar(title = { Text(text = "My App") }, actions = {
		IconButton(onClick = { viewModel.openSearchForm() }) {
			Icon(Icons.Filled.Search, "Search Icon")
		}
	})
}