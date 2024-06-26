package com.keunyoung.presentation.delegate

import androidx.navigation.NavHostController
import com.keunyoung.domain.model.Category

interface CategoryDelegate {
	fun openCategory(navHostController: NavHostController, category: Category)
}