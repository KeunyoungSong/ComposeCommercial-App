package com.keunyoung.domain.repository

import com.keunyoung.domain.model.BasketProduct
import com.keunyoung.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
	fun getBasketProducts() : Flow<List<BasketProduct>>
	
	suspend fun removeBasketProduct(product: Product)
	
	suspend fun checkoutBasket(productList: List<BasketProduct>)
}