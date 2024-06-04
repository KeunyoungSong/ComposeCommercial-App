package com.keunyoung.domain.repository

import com.keunyoung.domain.model.Category

interface CategoryRepository {
	fun getCategories(): List<Category>
}