package com.keunyoung.presentation.ui.category

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.keunyoung.domain.model.Category
import com.keunyoung.presentation.ui.component.ProductCard
import com.keunyoung.presentation.viewmodel.category.CategoryViewModel

@Composable
fun CategoryScreen(
	category: Category,
	navHostController: NavHostController,
	viewModel: CategoryViewModel = hiltViewModel(),
) {
	val productList by viewModel.productList.collectAsState()
	
	LaunchedEffect(key1 = category) {
		viewModel.updateCategory(category = category)
	}
	LazyColumn(
		modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(10.dp)
	) {
		items(productList.size) { index ->
			ProductCard(navHostController = navHostController, presentationVM = productList[index])
		}
	}
	
}