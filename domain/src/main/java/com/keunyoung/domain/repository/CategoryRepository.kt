package com.keunyoung.domain.repository

import com.keunyoung.domain.model.Category
import com.keunyoung.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
	fun getCategoryList(): Flow<List<Category>>
	fun getProductByCategory(category: Category): Flow<List<Product>>
	
	suspend fun likeProduct(product: Product)
}