package com.keunyoung.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.keunyoung.domain.model.Product
import com.keunyoung.presentation.R
import com.keunyoung.presentation.model.RankingVM

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RankingCard(navHostController: NavHostController, presentationVM: RankingVM) {
	val pagerState = rememberPagerState()
	val pageCount = presentationVM.model.productList.size / DEFAULT_RANKING_ITEM_COUNT
	
	Column {
		Text(text = presentationVM.model.title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
		HorizontalPager(
			count = pageCount, state = pagerState, contentPadding = PaddingValues(end = 140.dp)
		) { index ->
			Column {
				RankingProductCard(
					index * 3, presentationVM.model.productList[index * 3]
				) { product -> presentationVM.openRankingProduct(navHostController, product = product) }
				RankingProductCard(
					index * 3 + 1, presentationVM.model.productList[index * 3 + 1]
				) { product -> presentationVM.openRankingProduct(navHostController, product = product) }
				RankingProductCard(
					index * 3 + 2, presentationVM.model.productList[index * 3 + 2]
				) { product -> presentationVM.openRankingProduct(navHostController, product = product) }
			}
		}
	}
}

@Composable
fun RankingProductCard(index: Int, model: Product, onClick: (Product) -> Unit) {
	Row(
		modifier = Modifier
			.padding(10.dp)
			.fillMaxWidth()
	) {
		Text("${index + 1}", fontWeight = FontWeight.Bold)
		Image(
			painter = painterResource(id = R.drawable.product_image),
			contentDescription = null,
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.width(70.dp)
				.aspectRatio(0.7f)
		)
		Column(modifier = Modifier.padding(10.dp, 0.dp)) {
			Text(fontSize = 14.sp, text = model.shop.shopName, modifier = Modifier.padding(bottom = 4.dp))
			Text(fontSize = 14.sp, text = model.productName, modifier = Modifier.padding(bottom = 8.dp))
			Price(product = model)
		}
	}
}

private const val DEFAULT_RANKING_ITEM_COUNT = 3