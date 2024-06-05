package com.keunyoung.presentation.delegate

import androidx.navigation.NavHostController
import com.keunyoung.domain.model.Product

interface ProductDelegate {
	fun openProduct(navHostController: NavHostController, product: Product)
}