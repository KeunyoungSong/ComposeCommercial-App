package com.keunyoung.presentation.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.keunyoung.domain.model.ModelType
import com.keunyoung.presentation.model.BannerListVM
import com.keunyoung.presentation.model.BannerVM
import com.keunyoung.presentation.model.CarouselVM
import com.keunyoung.presentation.model.ProductVM
import com.keunyoung.presentation.model.RankingVM
import com.keunyoung.presentation.ui.component.BannerCard
import com.keunyoung.presentation.ui.component.BannerListCard
import com.keunyoung.presentation.ui.component.CarouselCard
import com.keunyoung.presentation.ui.component.ProductCard
import com.keunyoung.presentation.ui.component.RankingCard
import com.keunyoung.presentation.viewmodel.MainViewModel

@Composable
fun MainHomeScreen(navHostController: NavHostController, viewModel: MainViewModel) {
	val modelList by viewModel.modelList.collectAsState(initial = listOf())
	val columnCount by viewModel.columnCount.collectAsState()
	
	val testId = "ca-app-pub-3940256099942544/6300978111"
	val adId = "ca-app-pub-4798887709319566/9803580975"
	val adRequest = AdRequest.Builder().build()
	
	Column {
		AndroidView(modifier = Modifier
			.fillMaxWidth()
			.height(50.dp), factory = {
			AdView(it).apply {
				setAdSize(AdSize.BANNER)
				adUnitId = testId
				loadAd(adRequest)
			}
		}, update = {
			it.loadAd(adRequest)
		})
		LazyVerticalGrid(columns = GridCells.Fixed(columnCount)) {
			items(modelList.size, span = { index ->
				val item = modelList[index]
				val spanCount = getSpanCountByType(item.model.type, columnCount)
				GridItemSpan(spanCount)
			}) { index ->
				when (val item = modelList[index]) {
					is BannerVM -> BannerCard(presentationVM = item)
					is BannerListVM -> BannerListCard(presentationVM = item)
					is ProductVM -> ProductCard(navHostController = navHostController, presentationVM = item)
					is CarouselVM -> CarouselCard(navHostController = navHostController, presentationVM = item)
					is RankingVM -> RankingCard(navHostController = navHostController, presentationVM = item)
				}
			}
		}
	}
	
}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
	return when (type) {
		ModelType.PRODUCT -> 1
		ModelType.BANNER -> defaultColumnCount
		ModelType.BANNER_LIST -> defaultColumnCount
		ModelType.CAROUSEL -> defaultColumnCount
		ModelType.RANKING -> defaultColumnCount
	}
}
