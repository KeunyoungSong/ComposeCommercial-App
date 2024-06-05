package com.keunyoung.presentation.ui.component

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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.keunyoung.domain.model.Category
import com.keunyoung.domain.model.Price
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.SalesStatus
import com.keunyoung.domain.model.Shop
import com.keunyoung.presentation.R
import com.keunyoung.presentation.delegate.ProductDelegate
import com.keunyoung.presentation.model.ProductVM
import com.keunyoung.presentation.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(navHostController: NavHostController, presentationVM: ProductVM) {
	Card(shape = RoundedCornerShape(8.dp),
		modifier = Modifier
			.fillMaxWidth()
			.padding(10.dp)
			.height(intrinsicSize = IntrinsicSize.Max)
			.shadow(10.dp),
		onClick = { presentationVM.openProduct(navHostController, presentationVM.model) }) {
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
				text = presentationVM.model.shop.shopName, fontSize = 14.sp, fontWeight = FontWeight.SemiBold
			)
			Text(text = presentationVM.model.productName, fontSize = 14.sp)
			Price(presentationVM.model)
		}
	}
}

@Composable
fun Price(product: Product) {
	when (product.price.salesStatus) {
		SalesStatus.ON_SALE -> {
			Text(
				fontSize = 18.sp, fontWeight = FontWeight.Bold, text = "${product.price.originPrice}원"
			)
		}
		SalesStatus.ON_DISCOUNT -> {
			Text(
				text = "${product.price.originPrice}원",
				fontSize = 18.sp,
				fontWeight = FontWeight.Bold,
				textDecoration = TextDecoration.LineThrough
			)
			Row(verticalAlignment = Alignment.CenterVertically) {
				Text(fontSize = 14.sp, fontWeight = FontWeight.Bold, text = "할인가: ")
				Text(
					fontSize = 18.sp,
					fontWeight = FontWeight.Bold,
					text = "${product.price.finalPrice}원",
					color = Purple80
				)
			}
		}
		SalesStatus.SOLD_OUT -> {
			Text(
				fontSize = 18.sp, fontWeight = FontWeight.Bold, text = "판매종료", color = Color(0xFF666666)
			)
		}
	}
}

@Preview
@Composable
private fun PreviewProductCard() {
	ProductCard(navHostController = rememberNavController(), presentationVM = ProductVM(model = Product(
		productId = "1",
		productName = "상품 이름",
		imageUrl = "",
		price = Price(30000, 30000, SalesStatus.ON_SALE),
		category = Category.Top,
		shop = Shop("1", "샵 이름", ""),
		isNew = false,
		isFreeShipping = false
	), object : ProductDelegate {
		override fun openProduct(navHostController: NavHostController, product: Product) {
			TODO("Not yet implemented")
		}
	}))
}

@Preview
@Composable
private fun PreviewProductCardDiscount() {
	ProductCard(navHostController = rememberNavController(), presentationVM = ProductVM(model = Product(
		productId = "1",
		productName = "상품 이름",
		imageUrl = "",
		price = Price(30000, 20000, SalesStatus.ON_DISCOUNT),
		category = Category.Top,
		shop = Shop("1", "샵 이름", ""),
		isNew = false,
		isFreeShipping = false
	), object : ProductDelegate {
		override fun openProduct(navHostController: NavHostController, product: Product) {
			TODO("Not yet implemented")
		}
	}))
}

@Preview
@Composable
private fun PreviewProductCardSoldOut() {
	ProductCard(navHostController = rememberNavController(), presentationVM = ProductVM(model = Product(
		productId = "1",
		productName = "상품 이름",
		imageUrl = "",
		price = Price(30000, 20000, SalesStatus.SOLD_OUT),
		category = Category.Top,
		shop = Shop("1", "샵 이름", ""),
		isNew = false,
		isFreeShipping = false
	), object : ProductDelegate {
		override fun openProduct(navHostController: NavHostController, product: Product) {
			TODO("Not yet implemented")
		}
	}))
}
