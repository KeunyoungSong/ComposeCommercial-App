package com.keunyoung.presentation.ui.main

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.keunyoung.domain.model.Banner
import com.keunyoung.domain.model.BannerList
import com.keunyoung.domain.model.Carousel
import com.keunyoung.domain.model.ModelType
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.Ranking
import com.keunyoung.presentation.ui.component.BannerCard
import com.keunyoung.presentation.ui.component.BannerListCard
import com.keunyoung.presentation.ui.component.CarouselCard
import com.keunyoung.presentation.ui.component.ProductCard
import com.keunyoung.presentation.ui.component.RankingCard
import com.keunyoung.presentation.viewmodel.MainViewModel

@Composable
fun MainHomeScreen(viewModel: MainViewModel) {
	val modelList by viewModel.modelList.collectAsState(initial = listOf())
	val columnCount by viewModel.columnCount.collectAsState()
	LazyVerticalGrid(columns = GridCells.Fixed(columnCount)) {
		items(modelList.size, span = { index ->
			val item = modelList[index]
			val spanCount = getSpanCountByType(item.type, columnCount)
			GridItemSpan(spanCount)
		}) { index ->
			when (val item = modelList[index]) {
				is Banner -> BannerCard(model = item){
					viewModel.openBanner(it)
				}
				is BannerList -> BannerListCard(model = item){
					viewModel.openBannerList(it)
				}
				is Product -> ProductCard(model = item) {
					viewModel.openProduct(it)
				}
				is Carousel -> CarouselCard(model = item){
					viewModel.openCarouselProduct(it)
				}
				is Ranking -> RankingCard(model = item) {
					viewModel.openRankingProduct(it)
				}
			}
		}
	}
}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
	return when(type){
		ModelType.PRODUCT -> 1
		ModelType.BANNER -> defaultColumnCount
		ModelType.BANNER_LIST -> defaultColumnCount
		ModelType.CAROUSEL -> defaultColumnCount
		ModelType.RANKING -> defaultColumnCount
	}
}
