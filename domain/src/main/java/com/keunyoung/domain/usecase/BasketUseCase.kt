package com.keunyoung.domain.usecase

import com.keunyoung.domain.model.BasketProduct
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BasketUseCase @Inject constructor(
	private val basketRepository: BasketRepository
) {
	fun getBasketProductList() :Flow<List<BasketProduct>>{
		return basketRepository.getBasketProducts()
	}
	
	suspend fun removeBasketProduct(product: Product){
		basketRepository.removeBasketProduct(product)
	}
	
	suspend fun checkoutBasket(productList: List<BasketProduct>){
		basketRepository.checkoutBasket(productList)
	}
}