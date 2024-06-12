package com.keunyoung.presentation.ui.basket

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.keunyoung.domain.model.BasketProduct
import com.keunyoung.domain.model.Product
import com.keunyoung.presentation.ui.component.Price
import com.keunyoung.presentation.ui.popupSnackBar
import com.keunyoung.presentation.ui.theme.Purple80
import com.keunyoung.presentation.utils.NumberUtils
import com.keunyoung.presentation.viewmodel.basket.BasketAction
import com.keunyoung.presentation.viewmodel.basket.BasketEvent
import com.keunyoung.presentation.viewmodel.basket.BasketViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BasketScreen(snackbarHostState: SnackbarHostState, viewModel: BasketViewModel = hiltViewModel()) {
	val basketProductList by viewModel.basketProductList.collectAsState(listOf())
	val scope = rememberCoroutineScope()
	LaunchedEffect(Unit){
		viewModel.eventFlow.collectLatest {event ->
			when(event){
				BasketEvent.ShowSnackBar -> {
					popupSnackBar(scope, snackbarHostState, "결제 되었습니다.")
				}
			}
		}
	}
	
	Column(
		Modifier
			.fillMaxSize()
			.padding(16.dp)) {
		LazyColumn(modifier = Modifier
			.fillMaxSize()
			.weight(1f), contentPadding = PaddingValues(10.dp)) {
			items(basketProductList.size) { index ->
				BasketProductCard(basketProductList[index]) { product ->
					viewModel.dispatch(BasketAction.RemoveProduct(product))
				}
			}
		}
		Button(
			modifier = Modifier.fillMaxWidth(),
			onClick = { viewModel.dispatch(BasketAction.CheckoutBasket(basketProductList))},
			colors = ButtonDefaults.buttonColors(containerColor = Purple80),
			shape = RoundedCornerShape(12.dp)
		) {
			Icon(Icons.Filled.Check, contentDescription = null)
			Text(modifier = Modifier.fillMaxWidth().padding(5.dp), fontSize = 16.sp,  text = "${getTotalPrice(basketProductList)}원 결제하기")
		}
	}
}

@Composable
fun BasketProductCard(basketProduct: BasketProduct, removeProduct: (Product) -> Unit) {
	Box(modifier = Modifier.fillMaxWidth()) {
		Row(
			modifier = Modifier
				.padding(10.dp)
				.fillMaxWidth()
		) {
			AsyncImage(
				model = basketProduct.product.imageUrl,
				contentDescription = null,
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.width(80.dp)
					.aspectRatio(1f)
			)
			Column(
				modifier = Modifier
					.padding(10.dp, 0.dp)
					.weight(1f)
			) {
				Text(
					fontSize = 14.sp,
					text = basketProduct.product.shop.shopName,
					modifier = Modifier.padding(bottom = 4.dp)
				)
				Text(
					fontSize = 14.sp,
					text = basketProduct.product.productName,
					modifier = Modifier.padding(bottom = 8.dp)
				)
				Price(product = basketProduct.product)
			}
			Text(
				"${basketProduct.count} 개",
				fontSize = 18.sp,
				fontWeight = FontWeight.SemiBold,
				textAlign = TextAlign.Center,
				modifier = Modifier.padding(30.dp)
			)
		}
		IconButton(
			onClick = { removeProduct(basketProduct.product) }, modifier = Modifier.align(Alignment.TopEnd)
				.padding(start = 20.dp)
		) {
			Icon(Icons.Filled.Close, contentDescription = null)
		}
	}
}

private fun getTotalPrice(basketProductList: List<BasketProduct>): String {
	return basketProductList.sumOf {
		it.product.price.finalPrice * it.count
	}.let { totalPrice ->
		NumberUtils.numberFormatPrice(totalPrice)
	}
}