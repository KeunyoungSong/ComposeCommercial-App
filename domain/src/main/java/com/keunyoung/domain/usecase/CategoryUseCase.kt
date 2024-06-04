package com.keunyoung.domain.usecase

import com.keunyoung.domain.model.Category
import com.keunyoung.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryUseCase @Inject constructor(private val repository: CategoryRepository) {
	
	fun getCategories(): List<Category> {
		return repository.getCategories()
	}
}