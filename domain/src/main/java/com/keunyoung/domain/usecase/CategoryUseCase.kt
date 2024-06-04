package com.keunyoung.domain.usecase

import com.keunyoung.domain.model.Category
import com.keunyoung.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryUseCase @Inject constructor(private val repository: CategoryRepository) {
	
	fun getCategoryList(): Flow<List<Category>> {
		return repository.getCategoryList()
	}
}