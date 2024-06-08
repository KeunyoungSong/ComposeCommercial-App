package com.keunyoung.data.repository

import com.keunyoung.data.datasource.ProductDataSource
import com.keunyoung.data.db.dao.LikeDao
import com.keunyoung.data.db.dao.SearchDao
import com.keunyoung.data.db.entity.SearchKeywordEntity
import com.keunyoung.data.db.entity.toDomain
import com.keunyoung.data.db.entity.toLikeProductEntity
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.SearchFilter
import com.keunyoung.domain.model.SearchKeyword
import com.keunyoung.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
	private val dataSource: ProductDataSource, private val searchDao: SearchDao, private val likeDao: LikeDao
) : SearchRepository {
	
	override suspend fun search(
		searchKeyword: SearchKeyword, filters: List<SearchFilter>
	): Flow<List<Product>> {
		searchDao.upsert(SearchKeywordEntity(searchKeyword.keyword))
		return dataSource.getProducts().map { list ->
			list.filter { isAvailableProduct(it, searchKeyword, filters) }
		}.combine(likeDao.getAll()) { productList, likeList ->
			productList.map { product -> updateLikeStatus(product, likeList.map { it.productId }) }
		}
	}
	
	private fun isAvailableProduct(
		product: Product, searchKeyword: SearchKeyword, filters: List<SearchFilter>
	): Boolean {
		var isAvailable = true
		filters.forEach { isAvailable = isAvailable && it.isAvailableProduct(product) }
		return isAvailable && product.productName.contains(searchKeyword.keyword)
	}
	
	override fun getSearchKeywords(): Flow<List<SearchKeyword>> {
		return searchDao.getAll().map { it.map { entity -> entity.toDomain() } }
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