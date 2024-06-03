package com.keunyoung.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.SalesStatus
import com.keunyoung.presentation.R
import com.keunyoung.presentation.ui.theme.Purple80

@Composable
fun ProductCard(product: Product, onClick: (Product) -> Unit?) {
	Card(
		shape = RoundedCornerShape(8.dp),
		modifier = Modifier
			.fillMaxWidth()
			.padding(10.dp)
			.height(intrinsicSize = IntrinsicSize.Max)
			.shadow(10.dp)
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.height(280.dp)
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
					.aspectRatio(1f)
			)
			Text(text = product.shop.shopName, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
			Text(text = product.productName, fontSize = 14.sp)
		}
	}
}

@Composable
private fun Price(product: Product) {
	when (product.price.salesStatus) {
		SalesStatus.ON_SALE -> {
			Text(fontSize = 18.sp, fontWeight = FontWeight.Bold, text = "${product.price.originPrice}")
		}
		SalesStatus.ON_DISCOUNT -> {
			Text(
				text = "${product.price.originPrice}",
				fontSize = 18.sp,
				fontWeight = FontWeight.Bold,
				textDecoration = TextDecoration.LineThrough
			)
			Row {
				Text(fontSize = 14.sp, fontWeight = FontWeight.Bold, text = "할인가: ")
				Text(
					fontSize = 18.sp,
					fontWeight = FontWeight.Bold,
					text = "${product.price.finalPrice}",
					color = Purple80
				)
			}
		}
		SalesStatus.SOLD_OUT -> {
			Text(fontSize = 18.sp, fontWeight = FontWeight.Bold, text = "판매종료", color = Color(0xFF666666))
		}
	}
}

