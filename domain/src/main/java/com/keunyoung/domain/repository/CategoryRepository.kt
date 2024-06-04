package com.keunyoung.domain.repository

import com.keunyoung.domain.model.Category
import com.keunyoung.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
	fun getCategories(): Flow<List<Category>>
	fun getProductByCategory(category: Category): Flow<List<Product>>
}