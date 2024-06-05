package com.keunyoung.presentation.ui.product_detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.keunyoung.domain.model.Product

@Composable
fun ProductDetailScreen(productId: String) {
	Text("Product Detail Screen: $productId")
}