package com.keunyoung.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.SearchKeyword
import com.keunyoung.domain.usecase.SearchUseCase
import com.keunyoung.presentation.delegate.ProductDelegate
import com.keunyoung.presentation.model.ProductVM
import com.keunyoung.presentation.ui.NavigationRouteName
import com.keunyoung.presentation.utils.NavigationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
	private val useCase: SearchUseCase
) : ViewModel(), ProductDelegate {
	private val _searchResult = MutableStateFlow<List<ProductVM>>(listOf())
	val searchResult: StateFlow<List<ProductVM>> = _searchResult
	val searchKeywords = useCase.getSearchKeywords()
	
	fun search(keyword: String) {
		Log.d("result", "search called: $keyword")
		viewModelScope.launch {
			useCase.search(SearchKeyword(keyword = keyword)).collectLatest {
				Log.d("result", "search: $it")
				_searchResult.emit(it.map(::convertToProductVM))
			}
		}
	}
	
	private fun convertToProductVM(product: Product): ProductVM {
		return ProductVM(product, this)
	}
	
	override fun openProduct(navHostController: NavHostController, product: Product) {
		NavigationUtils.navigate(navHostController, NavigationRouteName.PRODUCT_DETAIL, product)
	}
}