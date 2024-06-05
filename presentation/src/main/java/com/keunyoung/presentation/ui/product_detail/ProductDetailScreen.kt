package com.keunyoung.presentation.ui.product_detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.keunyoung.presentation.viewmodel.product_detail.ProductDetailViewModel

@Composable
fun ProductDetailScreen(productId: String, viewModel: ProductDetailViewModel = hiltViewModel()) {
	
	val product by viewModel.product.collectAsState()
}