package com.keunyoung.data.repository

import com.keunyoung.domain.model.Category
import com.keunyoung.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(): CategoryRepository {
	override fun getCategories(): Flow<List<Category>> = flow {		// 서버로 부터 동적으로 받아옴
		emit(
			listOf(
				Category.Top,
				Category.Bag,
				Category.Dress,
				Category.Pants,
				Category.FashionAccessories,
				Category.Skirt,
				Category.Outerwear,
				Category.Shoes
			)
		)
	}
}