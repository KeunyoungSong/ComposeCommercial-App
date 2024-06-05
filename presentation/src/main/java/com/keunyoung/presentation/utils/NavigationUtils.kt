package com.keunyoung.presentation.utils

import android.net.Uri
import android.os.Parcelable
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.keunyoung.domain.model.Product

object NavigationUtils {
	
	fun navigate(
		controller: NavHostController,
		routeName: String,
		args: Any? = null,
		backStackRouteName: String? = null,
		isLaunchSingleTop: Boolean = true,
		needToRestoreState: Boolean = true
	) {
		// TODO 딥링크 시에도 사용
		var argument = ""
		if (args != null) {
			when (args) {
				is Parcelable -> {
					argument = String.format("/%s", Uri.parse(Gson().toJson(args)))
				}
				is Product -> {
					argument = String.format("/%s", Uri.parse(Gson().toJson(args)))
				}
			}
		}
		controller.navigate("$routeName$argument") {
			if (backStackRouteName != null) {
				popUpTo(backStackRouteName) { saveState = true }
			}
			launchSingleTop = isLaunchSingleTop
			restoreState = needToRestoreState
		}
	}
}