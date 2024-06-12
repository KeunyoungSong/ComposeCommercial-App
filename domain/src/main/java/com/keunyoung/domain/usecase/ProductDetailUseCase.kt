package com.keunyoung.domain.usecase

import com.keunyoung.domain.model.Product
import com.keunyoung.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductDetailUseCase @Inject constructor(
	private val repository: ProductDetailRepository
) {
	fun getProductDetail(productId: String): Flow<Product?> {
		return repository.getProductDetail(productId).run {
			this
		}
	}
	
	suspend fun addBasket(product: Product){
		repository.addProduct(product)
	}
}