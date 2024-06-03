package com.keunyoung.presentation.ui.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.keunyoung.presentation.common.ProductCard
import com.keunyoung.presentation.viewmodel.MainViewModel

@Composable
fun MainInsideScreen(viewModel: MainViewModel){
	val productList by viewModel.productList.collectAsState(initial = listOf())
	LazyColumn {
		items(productList.size){
			ProductCard(product = productList[it]) {
				// TODO: 상세화면 개발 시 추가
			}
		}
	}
}