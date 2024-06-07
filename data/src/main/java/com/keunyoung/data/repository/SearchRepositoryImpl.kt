package com.keunyoung.data.repository

import android.util.Log
import com.keunyoung.data.datasource.ProductDataSource
import com.keunyoung.data.db.dao.SearchDao
import com.keunyoung.data.db.entity.SearchKeywordEntity
import com.keunyoung.data.db.entity.toDomain
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.SearchFilter
import com.keunyoung.domain.model.SearchKeyword
import com.keunyoung.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
	private val dataSource: ProductDataSource, private val searchDao: SearchDao
) : SearchRepository {
	
	override suspend fun search(
		searchKeyword: SearchKeyword, filters: List<SearchFilter>
	): Flow<List<Product>> {
		searchDao.upsert(SearchKeywordEntity(searchKeyword.keyword))
		return dataSource.getProducts().map { list ->
			list.filter { isAvailableProduct(it, searchKeyword, filters) }
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
}