package com.keunyoung.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.keunyoung.presentation.viewmodel.MainViewModel

@Composable
fun CategoryScreen(
	viewModel: MainViewModel
) {
	val categoryList by viewModel.categoryList.collectAsState(initial = listOf())
	
}