package com.keunyoung.presentation.utils

import androidx.navigation.NavHostController
import com.keunyoung.presentation.ui.BasketNav
import com.keunyoung.presentation.ui.CategoryNav
import com.keunyoung.presentation.ui.Destination
import com.keunyoung.presentation.ui.MainNav
import com.keunyoung.presentation.ui.NavigationRouteName
import com.keunyoung.presentation.ui.ProductDetailNav
import com.keunyoung.presentation.ui.PurchaseHistoryNav
import com.keunyoung.presentation.ui.SearchNav

object NavigationUtils {
	
	fun navigate(
		controller: NavHostController,
		routeName: String,
		backStackRouteName: String? = null,
		isLaunchSingleTop: Boolean = true,
		needToRestoreState: Boolean = true
	) {
		controller.navigate(routeName) {
			if (backStackRouteName != null) {
				popUpTo(backStackRouteName) { saveState = true }
			}
			launchSingleTop = isLaunchSingleTop
			restoreState = needToRestoreState
		}
	}
	
	fun findDestination(route: String?): Destination {
		return when (route) {
			NavigationRouteName.MAIN_MY_PAGE -> MainNav.MyPage
			NavigationRouteName.MAIN_LIKE -> MainNav.Like
			NavigationRouteName.MAIN_HOME -> MainNav.Home
			NavigationRouteName.MAIN_CATEGORY -> MainNav.Category
			NavigationRouteName.SEARCH -> SearchNav
			NavigationRouteName.BASKET -> BasketNav
			NavigationRouteName.PURCHASE_HISTORY -> PurchaseHistoryNav
			
			CategoryNav.routeWithArgName()-> CategoryNav
			ProductDetailNav.routeWithArgName() -> ProductDetailNav
			else -> MainNav.Home
		}
	}
	
}