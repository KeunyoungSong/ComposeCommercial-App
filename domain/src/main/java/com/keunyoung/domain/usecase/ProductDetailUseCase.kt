package com.keunyoung.domain.usecase

import android.util.Log
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductDetailUseCase @Inject constructor(
	private val repository: ProductDetailRepository
) {
	fun getProductDetail(productId: String): Flow<Product?> {
		return repository.getProductDetail(productId).run {
			Log.d("ProductDetailUseCase", "id: $productId, getProductDetail: ${this.map { it?.productName }}")
			this
		}
	}
	
	suspend fun addBasket(product: Product){
		repository.addProduct(product)
	}
}