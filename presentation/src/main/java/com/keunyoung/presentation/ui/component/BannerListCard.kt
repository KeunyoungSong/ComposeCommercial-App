package com.keunyoung.presentation.ui.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.keunyoung.domain.model.BannerList
import com.keunyoung.presentation.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BannerListCard(model: BannerList, onClick: (BannerList) -> Unit) {
	val pagerState = rememberPagerState()
	LaunchedEffect(key1 = pagerState) {
		autoScrollInfinity(pagerState = pagerState)
	}
	HorizontalPager(count = model.imageList.size, state = pagerState) { pageNumber ->
		Card(
			shape = RoundedCornerShape(8.dp),
			modifier = Modifier
				.fillMaxWidth()
				.padding(10.dp)
				.shadow(10.dp),
			onClick = {onClick(model)}
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
		Box(
			modifier = Modifier.fillMaxWidth(),
		) {
			Text("pageNumber $pageNumber")
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