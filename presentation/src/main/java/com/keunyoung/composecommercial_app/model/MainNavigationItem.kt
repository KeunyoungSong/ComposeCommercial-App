package com.keunyoung.composecommercial_app.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.keunyoung.composecommercial_app.R

sealed class MainNavigationItem(var route: String, var name: String, val icon: ImageVector) {
	object Main : MainNavigationItem("Main", "Main", Icons.Filled.Home)
	object Category : MainNavigationItem("Category", "Category", Icons.Filled.Star)
	object MyPage : MainNavigationItem("MyPage", "MyPage", Icons.Filled.AccountBox)
}