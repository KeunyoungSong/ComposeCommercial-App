package com.keunyoung.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.keunyoung.presentation.ui.basket.BasketScreen
import com.keunyoung.presentation.ui.category.CategoryScreen
import com.keunyoung.presentation.ui.main.LikeScreen
import com.keunyoung.presentation.ui.main.MainCategoryScreen
import com.keunyoung.presentation.ui.main.MainHomeScreen
import com.keunyoung.presentation.ui.main.MyPageScreen
import com.keunyoung.presentation.ui.product_detail.ProductDetailScreen
import com.keunyoung.presentation.ui.purchase_history.PurchaseHistoryScreen
import com.keunyoung.presentation.ui.search.SearchScreen
import com.keunyoung.presentation.utils.NavigationUtils
import com.keunyoung.presentation.viewmodel.MainViewModel

@Composable
fun MainNavigationScreen(googleSignInClient: GoogleSignInClient) {
	val viewModel = hiltViewModel<MainViewModel>()
	val navHostController = rememberNavController()
	val navBackStackEntry by navHostController.currentBackStackEntryAsState()
	val currentRoute = navBackStackEntry?.destination?.route
	
	Scaffold(topBar = {
		MainHeader(viewModel, navHostController, currentRoute)
	}, bottomBar = {
		if (MainNav.isMainRoute(currentRoute)) MainBottomNavigationBar(
			navHostController, currentRoute
		)
	}) { innerPadding ->
		MainNavigationScreen(
			navController = navHostController,
			innerPadding = innerPadding,
			viewModel = viewModel,
			googleSignInClient = googleSignInClient
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHeader(viewModel: MainViewModel, navHostController: NavHostController, currentRoute: String?) {
	TopAppBar(title = {
		val title = NavigationUtils.findDestination(currentRoute).title
		Text(text = title)
	}, navigationIcon = {
		if (!MainNav.isMainRoute(currentRoute)) {
			IconButton(onClick = { navHostController.popBackStack() }) {
				Icon(Icons.Filled.ArrowBack, contentDescription = null)
			}
		}
	}, actions = {
		if (MainNav.isMainRoute(currentRoute)) {
			IconButton(onClick = { viewModel.openSearchForm(navHostController) }) {
				Icon(Icons.Filled.Search, "Search Icon")
			}
			IconButton(onClick = { viewModel.openBasket(navHostController) }) {
				Icon(Icons.Filled.ShoppingCart, "Shopping Icon")
			}
		}
	})
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController, currentRoute: String?) {
	val bottomNavigationItems = listOf(
		MainNav.Home,
		MainNav.Category,
		MainNav.Like,
		MainNav.MyPage,
	)
	
	BottomAppBar(
		containerColor = MaterialTheme.colorScheme.primaryContainer,
		contentColor = MaterialTheme.colorScheme.primary
	) {
		bottomNavigationItems.forEach { navItem ->
			NavigationBarItem(selected = currentRoute == navItem.route, icon = {
				Icon(navItem.icon, navItem.route)
			}, onClick = {
				NavigationUtils.navigate(
					navController, navItem.route, navController.graph.startDestinationRoute
				)
			})
		}
	}
}

@Composable
fun MainNavigationScreen(
	navController: NavHostController,
	innerPadding: PaddingValues,
	viewModel: MainViewModel,
	googleSignInClient: GoogleSignInClient
) {
	NavHost(
		modifier = Modifier.padding(innerPadding),
		navController = navController,
		startDestination = MainNav.Home.route
	) {
		composable(
			route = MainNav.Home.route, deepLinks = MainNav.Home.deepLinks
		) {
			MainHomeScreen(navHostController = navController, viewModel = viewModel)
		}
		composable(route = MainNav.Category.route, deepLinks = MainNav.Category.deepLinks) {
			MainCategoryScreen(viewModel = viewModel, navController = navController)
		}
		composable(route = MainNav.MyPage.route, deepLinks = MainNav.MyPage.deepLinks) {
			MyPageScreen(
				viewModel = viewModel,
				googleSignInClient = googleSignInClient,
				navHostController = navController
			)
		}
		composable(route = MainNav.Like.route, deepLinks = MainNav.Like.deepLinks) {
			LikeScreen(navHostController = navController, viewModel = viewModel)
		}
		composable(route = BasketNav.route, deepLinks = BasketNav.deepLinks) {
			BasketScreen()
		}
		composable(route = SearchNav.route, deepLinks = SearchNav.deepLinks) {
			SearchScreen(navHostController = navController)
		}
		composable(route = PurchaseHistoryNav.route, deepLinks = PurchaseHistoryNav.deepLinks) {
			PurchaseHistoryScreen()
		}
		composable(
			route = CategoryNav.routeWithArgName(),
			arguments = CategoryNav.arguments,
			deepLinks = CategoryNav.deepLinks
		) { navBackStackEntry ->
			val category = CategoryNav.findArgument(navBackStackEntry)
			if (category != null) CategoryScreen(navHostController = navController, category = category)
		}
		composable(
			route = ProductDetailNav.routeWithArgName(),
			arguments = ProductDetailNav.arguments,
			deepLinks = ProductDetailNav.deepLinks
		) { navBackStackEntry ->
			val productId = ProductDetailNav.findArgument(navBackStackEntry)
			Log.d("productId", "MainScreen productId: $productId")
			if (productId != null) {
				ProductDetailScreen(productId)
			}
		}
		
	}
}

