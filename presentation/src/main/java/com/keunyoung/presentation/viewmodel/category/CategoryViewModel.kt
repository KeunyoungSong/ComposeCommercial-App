package com.keunyoung.presentation.viewmodel.category

import androidx.lifecycle.ViewModel
import com.keunyoung.domain.model.Category
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.usecase.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
	private val useCase: CategoryUseCase
) : ViewModel() {
	private val _productList = MutableStateFlow<List<Product>>(listOf())
	val productList: StateFlow<List<Product>> = _productList
	
	suspend fun updateCategory(category: Category) {
		useCase.getProductsByCategory(category = category).collectLatest {
			_productList.emit(it)
		}
	}
}