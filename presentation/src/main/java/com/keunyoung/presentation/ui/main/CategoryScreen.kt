package com.keunyoung.presentation.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.keunyoung.presentation.viewmodel.MainViewModel

@Composable
fun CategoryScreen(
	viewModel: MainViewModel
) {
	val categoryList by viewModel.categoryList.collectAsState(initial = listOf())
	
	LazyVerticalGrid(columns = GridCells.Fixed(3)) {
		items(categoryList.size, span = { GridItemSpan(1) }) { index ->
			Card(
				shape = RoundedCornerShape(4.dp),
				modifier = Modifier
					.height(100.dp)
					.fillMaxWidth()
					.padding(10.dp)
					.shadow(10.dp)
			) {
				Text(
					text = categoryList[index].categoryName,
					modifier = Modifier
						.fillMaxSize()
						.padding(10.dp),
					textAlign = TextAlign.Center
				)
			}
		}
	}
}