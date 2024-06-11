package com.keunyoung.presentation.ui.purchase_history

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.keunyoung.domain.model.BasketProduct
import com.keunyoung.domain.model.PurchaseHistory
import com.keunyoung.presentation.R
import com.keunyoung.presentation.ui.component.Price
import com.keunyoung.presentation.utils.NumberUtils
import com.keunyoung.presentation.viewmodel.PurchaseHistoryViewModel

@Composable
fun PurchaseHistoryScreen(
	viewModel: PurchaseHistoryViewModel = hiltViewModel()
) {
	val purchaseHistory by viewModel.purchaseHistory.collectAsState(listOf())
	
	LazyColumn(modifier = Modifier
		.fillMaxWidth()
		.padding(10.dp)) {
		purchaseHistory.forEach{
			PurchaseHistoryCard(purchaseHistory = it)
		}
	}
}

fun LazyListScope.PurchaseHistoryCard(purchaseHistory: PurchaseHistory) {
	item {
		Text(fontSize = 16.sp, text = "${purchaseHistory.purchaseAt}")
	}
	items(purchaseHistory.basketList.size) { index ->
		val currentItem = purchaseHistory.basketList[index]
		Row(modifier = Modifier.padding(10.dp)) {
			Image(
				painter = painterResource(R.drawable.product_image),
				contentDescription = null,
				contentScale = ContentScale.Crop,
				modifier = Modifier.size(60.dp)
			)
			Column(
				modifier = Modifier
					.padding(10.dp)
					.weight(1f)
			) {
				Text(
					fontSize = 14.sp,
					text = "${currentItem.product.shop.shopName} - ${currentItem.product.productName}",
					modifier = Modifier.padding(10.dp)
				)
				Price(product = currentItem.product)
			}
			Text(
				text = "${currentItem.count}개",
				fontSize = 18.sp,
				fontWeight = FontWeight.SemiBold,
				textAlign = TextAlign.Center,
				modifier = Modifier.padding(30.dp)
			)
		}
	}
	item {
		Text(
			modifier = Modifier
				.fillMaxWidth()
				.padding(5.dp),
			fontSize = 16.sp,
			text = "${getTotalPrice(purchaseHistory.basketList)} 결제완료"
		)
	}
}

private fun getTotalPrice(basketProductList: List<BasketProduct>): String {
	return basketProductList.sumOf {
		it.product.price.finalPrice * it.count
	}.let { totalPrice ->
		NumberUtils.numberFormatPrice(totalPrice)
	}
}