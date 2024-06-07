package com.keunyoung.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import com.keunyoung.domain.model.Category
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.SearchFilter
import com.keunyoung.domain.model.SearchKeyword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.max
import kotlin.math.min

class SearchManager {
	// 키워드 저장, 필터 저장, 검색 결과 기반 필터 구성, 필터 업데이트
	// 필터를 뷰에서 가져와 처리
	private val _filters = MutableStateFlow<List<SearchFilter>>(listOf())
	val filters: StateFlow<List<SearchFilter>> = _filters
	
	var searchKeyword = SearchKeyword("")
		private set
	
	suspend fun initSearchManager(newSearchKeyword: String, searchResult: List<Product>) {
		val categoryList = mutableStateListOf<Category>()
		var minPrice = Int.MAX_VALUE
		var maxPrice = Int.MIN_VALUE
		
		searchResult.forEach { product ->
			if (categoryList.find { it.categoryId == product.category.categoryId } == null) {
				categoryList.add(product.category)
			}
			minPrice = min(minPrice, product.price.finalPrice)
			maxPrice = max(maxPrice, product.price.finalPrice)
		}
		
		_filters.emit(
			listOf(
				SearchFilter.PriceFilter(minPrice.toFloat() to maxPrice.toFloat()),
				SearchFilter.CategoryFilter(categoryList)
			)
		)
		searchKeyword = SearchKeyword(newSearchKeyword)
	}
	
	fun clearFilter() {
		_filters.value.forEach {
			it.clear()
		}
	}
	
	suspend fun updateFilter(filter: SearchFilter) {
		val currentFilter = _filters.value
		
		_filters.emit(currentFilter.map {
			if (it is SearchFilter.PriceFilter && filter is SearchFilter.PriceFilter) {
				it.selectedRange = filter.selectedRange
			}
			if (it is SearchFilter.CategoryFilter && filter is SearchFilter.CategoryFilter) {
				it.selectedCategory = filter.selectedCategory
			}
			it
		})
	}
	
	fun currentFilters(): List<SearchFilter> = _filters.value
}