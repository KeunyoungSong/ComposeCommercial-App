package com.keunyoung.presentation.ui.main

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.keunyoung.presentation.common.ProductCard
import com.keunyoung.presentation.viewmodel.MainViewModel

@Composable
fun MainInsideScreen(viewModel: MainViewModel){
	val modelList by viewModel.modelList.collectAsState(initial = listOf())
	val columnCount by viewModel.columnCount.collectAsState()
	LazyVerticalGrid(columns = GridCells.Fixed(columnCount)) {
		items(modelList.size){
//			ProductCard(product = modelList[it]) {
//				// TODO: 상세화면 개발 시 추가
//			}
		}
	}
}