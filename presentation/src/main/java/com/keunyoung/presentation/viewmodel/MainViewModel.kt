package com.keunyoung.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.keunyoung.domain.model.BaseModel
import com.keunyoung.domain.model.Category
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.usecase.CategoryUseCase
import com.keunyoung.domain.usecase.MainUseCase
import com.keunyoung.presentation.delegate.BannerDelegate
import com.keunyoung.presentation.delegate.CategoryDelegate
import com.keunyoung.presentation.delegate.ProductDelegate
import com.keunyoung.presentation.ui.NavigationRouteName
import com.keunyoung.presentation.utils.NavigationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	mainUseCase: MainUseCase, categoryUseCase: CategoryUseCase
) : ViewModel(), ProductDelegate, BannerDelegate, CategoryDelegate {
	private val _columnCount = MutableStateFlow(DEFAULT_COLUMN_COUNT)
	val columnCount: StateFlow<Int> = _columnCount
	
	val modelList: Flow<List<BaseModel>> = mainUseCase.getModelList()
	val categoryList: Flow<List<Category>> = categoryUseCase.getCategoryList()
	
	fun openSearchForm() {}
	
	fun updateColumnCount(count: Int) {
		viewModelScope.launch {
			_columnCount.emit(count)
		}
	}
	
	override fun openProduct(product: Product) {
		// TODO
	}
	
	override fun openBanner(bannerId: String) {
	
	}
	
	override fun openCategory(navHostController: NavHostController, category: Category) {
		NavigationUtils.navigate(
			navHostController, NavigationRouteName.CATEGORY, category
		)
	}
	
	
	companion object {
		private const val DEFAULT_COLUMN_COUNT = 2
	}
	
}