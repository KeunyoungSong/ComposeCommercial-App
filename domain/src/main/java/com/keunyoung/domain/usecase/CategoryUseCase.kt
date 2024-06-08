package com.keunyoung.domain.usecase

import com.keunyoung.domain.model.Category
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {
	
	fun getCategoryList(): Flow<List<Category>> {
		return categoryRepository.getCategoryList()
	}
	
	fun getProductsByCategory(category: Category): Flow<List<Product>> {
		return categoryRepository.getProductByCategory(category)
	}
	
	suspend fun likeProduct(product: Product){
		categoryRepository.likeProduct(product)
	}
}