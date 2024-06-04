package com.keunyoung.domain.repository

import com.keunyoung.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
	fun getCategories(): Flow<List<Category>>
}