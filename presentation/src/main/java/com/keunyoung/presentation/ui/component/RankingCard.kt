package com.keunyoung.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.keunyoung.domain.model.Product
import com.keunyoung.presentation.model.RankingVM

private const val DEFAULT_RANKING_ITEM_COUNT = 3

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RankingCard(navHostController: NavHostController, presentationVM: RankingVM) {
	val pagerState = rememberPagerState()
	val pageCount = presentationVM.model.productList.size / DEFAULT_RANKING_ITEM_COUNT
	
	Column {
		Text(text = presentationVM.model.title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold,
			modifier = Modifier.padding(10.dp))
		HorizontalPager(
			count = pageCount, state = pagerState, contentPadding = PaddingValues(end = 70.dp)
		) { index ->
			Column {
				RankingProductCard(
					index = index * 3,
					model = presentationVM.model.productList[index * 3],
					rankingVM = presentationVM
				) { product -> presentationVM.openRankingProduct(navHostController, product = product) }
				RankingProductCard(
					index = index * 3 + 1,
					model = presentationVM.model.productList[index * 3 + 1],
					rankingVM = presentationVM
				) { product -> presentationVM.openRankingProduct(navHostController, product = product) }
				RankingProductCard(
					index = index * 3 + 2,
					model = presentationVM.model.productList[index * 3 + 2],
					rankingVM = presentationVM
				) { product -> presentationVM.openRankingProduct(navHostController, product = product) }
			}
		}
	}
}

@Composable
fun RankingProductCard(index: Int, model: Product, rankingVM: RankingVM, onClick: (Product) -> Unit) {
	Box(modifier = Modifier.fillMaxWidth()) {
		IconButton(
			onClick = { rankingVM.likeProduct(model) }, modifier = Modifier.align(Alignment.BottomEnd)
		) {
			Icon(
				imageVector = if (model.isLike) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
				contentDescription = null
			)
		}
		Row(
			modifier = Modifier
				.padding(10.dp)
				.fillMaxWidth()
		) {
			
			Text("${index + 1}", fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 4.dp))
			AsyncImage(model = model.imageUrl,
				contentDescription = null,
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.width(120.dp)
					.aspectRatio(4/3f)
					.clickable { onClick(model) })
			Column(modifier = Modifier.padding(10.dp, 0.dp)) {
				Text(
					fontSize = 14.sp, text = model.shop.shopName, modifier = Modifier.padding(bottom = 2.dp)
				)
				Text(
					fontSize = 14.sp, text = model.productName, modifier = Modifier.padding(bottom = 4.dp)
				)
				Price(product = model)
			}
		}
	}
}

