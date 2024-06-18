package com.keunyoung.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.SalesStatus
import com.keunyoung.presentation.model.ProductVM
import com.keunyoung.presentation.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(navHostController: NavHostController, presentationVM: ProductVM) {
	Card(shape = RoundedCornerShape(8.dp),
		modifier = Modifier
			.fillMaxWidth()
			.padding(10.dp),
		onClick = { presentationVM.openProduct(navHostController, presentationVM.model) }) {
		Box(modifier = Modifier.fillMaxWidth()) {
			IconButton(
				onClick = { presentationVM.likeProduct(presentationVM.model) },
				modifier = Modifier.align(Alignment.BottomEnd)
			) {
				Icon(
					imageVector = if (presentationVM.model.isLike) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
					contentDescription = null
				)
			}
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(10.dp),
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.Start
			) {
				AsyncImage(
					model = presentationVM.model.imageUrl,
					contentDescription = null,
					modifier = Modifier
						.fillMaxWidth()
						.aspectRatio(2f),
					contentScale = ContentScale.Crop
				)
				Text(
					text = presentationVM.model.shop.shopName,
					fontSize = 14.sp,
					fontWeight = FontWeight.SemiBold
				)
				Text(text = presentationVM.model.productName, fontSize = 14.sp)
				Price(presentationVM.model)
			}
		}
	}
}

@Composable
fun Price(product: Product) {
	when (product.price.salesStatus) {
		SalesStatus.ON_SALE -> {
			Text(
				fontSize = 16.sp, fontWeight = FontWeight.Bold, text = "${product.price.originPrice}원"
			)
		}
		SalesStatus.ON_DISCOUNT -> {
			Text(
				text = "${product.price.originPrice}원",
				fontSize = 14.sp,
				fontWeight = FontWeight.Bold,
				textDecoration = TextDecoration.LineThrough
			)
			Row(verticalAlignment = Alignment.CenterVertically) {
				Text(fontSize = 12.sp, fontWeight = FontWeight.Bold, text = "할인가: ")
				Text(
					fontSize = 16.sp,
					fontWeight = FontWeight.Bold,
					text = "${product.price.finalPrice}원",
					color = Color.Red
				)
			}
		}
		SalesStatus.SOLD_OUT -> {
			Text(
				fontSize = 16.sp, fontWeight = FontWeight.Bold, text = "판매종료", color = Color(0xFF666666)
			)
		}
	}
}
