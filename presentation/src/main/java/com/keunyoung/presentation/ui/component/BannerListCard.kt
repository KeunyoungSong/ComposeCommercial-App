package com.keunyoung.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.keunyoung.presentation.model.BannerListVM
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BannerListCard(presentationVM: BannerListVM) {
	val pagerState = rememberPagerState()
	LaunchedEffect(key1 = pagerState) {
		autoScrollInfinity(pagerState = pagerState)
	}
	HorizontalPager(count = presentationVM.model.imageList.size, state = pagerState) { pageNumber ->
		Card(shape = RoundedCornerShape(8.dp),
			modifier = Modifier
				.fillMaxWidth()
				.padding(10.dp),
			onClick = { presentationVM.openBannerList(presentationVM.model.bannerId) }) {
			AsyncImage(
				model = presentationVM.model.imageList[pageNumber],
				contentDescription = null,
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.fillMaxWidth()
					.aspectRatio(2f)
			)
			Box(
				modifier = Modifier.fillMaxWidth()
			) {
				Text(
					"PageNumber ${pageNumber + 1}",
					textAlign = TextAlign.End,
					modifier = Modifier.align(Alignment.BottomEnd).padding(2.dp)
				)
			}
		}
	}
}

@OptIn(ExperimentalPagerApi::class)
private suspend fun autoScrollInfinity(pagerState: PagerState) {
	while (true) {
		delay(3000)
		val currentPage = pagerState.currentPage
		val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
		pagerState.animateScrollToPage(nextPage)
	}
}