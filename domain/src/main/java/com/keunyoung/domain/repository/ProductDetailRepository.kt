package com.keunyoung.domain.repository

import com.keunyoung.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductDetailRepository {
	fun getProductDetail(productId: String) : Flow<Product?>
	
	suspend fun addProduct(product: Product)
}