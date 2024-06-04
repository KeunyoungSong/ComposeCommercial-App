package com.keunyoung.data.repository

import com.keunyoung.data.datasource.ProductDataSource
import com.keunyoung.domain.model.Category
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
	private val dataSource: ProductDataSource
) : CategoryRepository {
	override fun getCategoryList(): Flow<List<Category>> = flow {        // 서버로 부터 동적으로 받아옴
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
	
	override fun getProductByCategory(category: Category): Flow<List<Product>> {
		return dataSource.getProducts().map { list ->
			list.filter { product -> product.category == category }
		}
	}
}