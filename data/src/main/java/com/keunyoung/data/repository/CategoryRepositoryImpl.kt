package com.keunyoung.data.repository

import com.keunyoung.data.datasource.ProductDataSource
import com.keunyoung.data.db.dao.LikeDao
import com.keunyoung.data.db.entity.toLikeProductEntity
import com.keunyoung.domain.model.Category
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
	private val dataSource: ProductDataSource, private val likeDao: LikeDao
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
		return dataSource.getHomeComponentList().map { list ->
			list.filterIsInstance<Product>()
				.filter { product -> product.category.categoryId == category.categoryId }
		}.combine(likeDao.getAll()) { productList, likeList ->
			productList.map { product -> updateLikeStatus(product, likeList.map { it.productId }) }
		}
	}
	
	override suspend fun likeProduct(product: Product) {
		if (product.isLike) {
			likeDao.delete(product.productId)
		} else {
			likeDao.upsert(product.toLikeProductEntity().copy(isLike = true))
		}
	}
	
	private fun updateLikeStatus(product: Product, likeProductIds: List<String>): Product {
		return product.copy(isLike = likeProductIds.contains(product.productId))
	}
}