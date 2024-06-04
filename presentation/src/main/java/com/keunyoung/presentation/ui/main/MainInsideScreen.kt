package com.keunyoung.presentation.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.keunyoung.domain.model.Banner
import com.keunyoung.domain.model.BannerList
import com.keunyoung.domain.model.ModelType
import com.keunyoung.domain.model.Product
import com.keunyoung.presentation.R
import com.keunyoung.presentation.component.ProductCard
import com.keunyoung.presentation.viewmodel.MainViewModel

@Composable
fun MainInsideScreen(viewModel: MainViewModel) {
	val modelList by viewModel.modelList.collectAsState(initial = listOf())
	val columnCount by viewModel.columnCount.collectAsState()
	LazyVerticalGrid(columns = GridCells.Fixed(columnCount)) {
		items(modelList.size, span = { index ->
			val item = modelList[index]
			val spanCount = getSpanCountByType(item.type, columnCount)
			GridItemSpan(spanCount)
		}) { index ->
			when (val item = modelList[index]) {
				is Banner -> BannerCard(model = item)
				is Product -> ProductCard(product = item) {}
				is BannerList -> TODO()
			}
		}
	}
}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
	return when(type){
		ModelType.PRODUCT -> 1
		ModelType.BANNER -> defaultColumnCount
		ModelType.BANNER_LIST -> defaultColumnCount
	}
}

@Composable
fun BannerCard(model: Banner) {
	Card(
		shape = RoundedCornerShape(8.dp), modifier = Modifier
			.fillMaxWidth()
			.padding(10.dp)
			.shadow(10.dp)
	) {
		Image(
			painter = painterResource(id = R.drawable.product_image),
			contentDescription = null,
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.fillMaxWidth()
				.aspectRatio(2f)
		)
	}
}