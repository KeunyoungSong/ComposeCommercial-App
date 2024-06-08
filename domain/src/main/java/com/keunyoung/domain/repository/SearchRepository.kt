package com.keunyoung.domain.repository

import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.SearchFilter
import com.keunyoung.domain.model.SearchKeyword
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
	// 검색 결과 가져오기
	suspend fun search(searchKeyword: SearchKeyword, filters: List<SearchFilter>) : Flow<List<Product>>
	
	// 최근 검색어 가져오기
	fun getSearchKeywords() : Flow<List<SearchKeyword>>
	
	suspend fun likeProduct(product: Product)
}