package com.keunyoung.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keunyoung.domain.model.Banner
import com.keunyoung.domain.model.BannerList
import com.keunyoung.domain.model.BaseModel
import com.keunyoung.domain.model.Category
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.usecase.CategoryUseCase
import com.keunyoung.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	mainUseCase: MainUseCase, categoryUseCase: CategoryUseCase
) : ViewModel() {
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
	
	fun openProduct(product: Product) {
	
	}
	
	fun openCarouselProduct(product: Product) {
	
	}
	
	fun openBannerList(bannerList: BannerList) {
	
	}
	
	fun openBanner(banner: Banner) {
	
	}
	
	fun openRankingProduct(product: Product) {
	
	}
	
	companion object {
		private const val DEFAULT_COLUMN_COUNT = 2
	}
}