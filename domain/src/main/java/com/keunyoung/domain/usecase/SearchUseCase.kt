package com.keunyoung.domain.usecase

import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.SearchFilter
import com.keunyoung.domain.model.SearchKeyword
import com.keunyoung.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
	private val searchRepository: SearchRepository
) {
	suspend fun search(searchKeyword: SearchKeyword, filters: List<SearchFilter>): Flow<List<Product>> {
		return searchRepository.search(searchKeyword, filters)
	}
	
	fun getSearchKeywords(): Flow<List<SearchKeyword>> {
		return searchRepository.getSearchKeywords()
	}
}