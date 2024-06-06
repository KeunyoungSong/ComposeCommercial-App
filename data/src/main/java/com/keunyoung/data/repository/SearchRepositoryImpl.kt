package com.keunyoung.data.repository

import com.keunyoung.data.datasource.ProductDataSource
import com.keunyoung.data.db.dao.SearchDao
import com.keunyoung.data.db.entity.SearchKeywordEntity
import com.keunyoung.data.db.entity.toDomain
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.SearchKeyword
import com.keunyoung.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
	private val dataSource: ProductDataSource, private val searchDao: SearchDao
) : SearchRepository {
	override suspend fun search(searchKeyword: SearchKeyword): Flow<List<Product>> {
		searchDao.upsert(SearchKeywordEntity(searchKeyword.keyword))
		return dataSource.getProducts().map { list ->
			list.filter { it.productName.contains(searchKeyword.keyword) }
		}
	}
	
	override fun getSearchKeywords(): Flow<List<SearchKeyword>> {
		return searchDao.getAll().map { it.map { entity -> entity.toDomain() } }
	}
}