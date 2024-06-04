package com.keunyoung.data.repository

import com.keunyoung.domain.model.Category
import com.keunyoung.domain.repository.CategoryRepository

class CategoryRepositoryImpl : CategoryRepository {
	override fun getCategories(): List<Category> {		// 서버로 부터 동적으로 받아옴
		return listOf(
			Category.Top,
			Category.Bag,
			Category.Dress,
			Category.Pants,
			Category.FashionAccessories,
			Category.Skirt,
			Category.Outerwear,
			Category.Shoes
		)
	}
}