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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.keunyoung.domain.model.Category
import com.keunyoung.presentation.ui.category.CategoryScreen
import com.keunyoung.presentation.ui.main.MainCategoryScreen
import com.keunyoung.presentation.ui.main.MainHomeScreen
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
	val navBackStackEntry by navHostController.currentBackStackEntryAsState()
	val currentRoute = navBackStackEntry?.destination?.route
	
	Scaffold(topBar = {
		TopAppBar(viewModel)
	}, bottomBar = {
		if (NavigationItem.MainNav.isMainRoute(currentRoute)) BottomAppBar(
			navHostController, currentRoute
		)
	}) { innerPadding ->
		MainScreen(
			navController = navHostController,
			innerPadding = innerPadding,
			viewModel = viewModel
		)
	}
}

@Composable
fun BottomAppBar(navController: NavController, currentRoute: String?) {
	val bottomNavigationItems = listOf(
		NavigationItem.MainNav.Home,
		NavigationItem.MainNav.Category,
		NavigationItem.MainNav.MyPage,
	)
	
	BottomAppBar(
		containerColor = MaterialTheme.colorScheme.primaryContainer,
		contentColor = MaterialTheme.colorScheme.primary
	) {
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
fun MainScreen(
	navController: NavHostController,
	innerPadding: PaddingValues,
	viewModel: MainViewModel
) {
	NavHost(
		modifier = Modifier.padding(innerPadding),
		navController = navController,
		startDestination = NavigationRouteName.MAIN_HOME
	) {
		composable(NavigationRouteName.MAIN_HOME) {
			MainHomeScreen(viewModel = viewModel)
		}
		composable(NavigationRouteName.MAIN_CATEGORY) {
			MainCategoryScreen(viewModel = viewModel)
		}
		composable(NavigationRouteName.MAIN_MY_PAGE) {
			Text("MyPage")
		}
		composable(
			route = NavigationRouteName.CATEGORY + "/{category}",
			arguments = listOf(navArgument("category") { type = NavType.StringType })
		) { navBackStackEntry ->
			val categoryString = navBackStackEntry.arguments?.getString("category")
			val category = Gson().fromJson(categoryString, Category::class.java)
			if (category != null) {
				CategoryScreen(category = category)
			}
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