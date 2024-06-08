package com.keunyoung.presentation.ui.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.keunyoung.presentation.model.ProductVM
import com.keunyoung.presentation.ui.component.ProductCard
import com.keunyoung.presentation.viewmodel.MainViewModel

@Composable
fun LikeScreen(
	navHostController: NavHostController,
	viewModel: MainViewModel
){
	val likeProductList by viewModel.likeProductList.collectAsState(initial = listOf())
	
	LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(10.dp)) {
		items(likeProductList.size){index ->
			ProductCard(navHostController = navHostController, presentationVM = likeProductList[index] as ProductVM)
		}
	}
}