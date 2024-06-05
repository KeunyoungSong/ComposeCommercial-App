package com.keunyoung.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keunyoung.domain.model.Product
import com.keunyoung.presentation.R
import com.keunyoung.presentation.model.CarouselVM

@Composable
fun CarouselCard(presentationVM: CarouselVM) {
	val scrollState = rememberLazyListState()
	Column {
		Text(
			text = presentationVM.model.title,
			fontSize = 14.sp,
			fontWeight = FontWeight.SemiBold,
			modifier = Modifier.padding(10.dp)
		)
		LazyRow(
			state = scrollState, modifier = Modifier
				.fillMaxWidth()
				.wrapContentWidth()
		) {
			items(presentationVM.model.productList.size) { index ->
				CarouselProductCard(model = presentationVM.model.productList[index]) {
					presentationVM.openCarouselProduct(presentationVM.model.productList[index])
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CarouselProductCard(model: Product, onClick: (Product) -> Unit) {
	Card(shape = RoundedCornerShape(8.dp),
		modifier = Modifier
			.width(150.dp)
			.wrapContentHeight()
			.padding(10.dp),
		onClick = { onClick }) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(10.dp),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.Start
		) {
			Image(
				painter = painterResource(id = R.drawable.product_image),
				contentDescription = null,
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.fillMaxWidth()
					.aspectRatio(2f)
			)
			Text(
				text = model.shop.shopName, fontSize = 14.sp, fontWeight = FontWeight.SemiBold
			)
			Text(text = model.productName, fontSize = 14.sp)
			Price(model)
		}
	}
}