package com.keunyoung.presentation.viewmodel.category

import androidx.lifecycle.ViewModel
import com.keunyoung.domain.model.Category
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.usecase.CategoryUseCase
import com.keunyoung.presentation.delegate.ProductDelegate
import com.keunyoung.presentation.model.ProductVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
	private val useCase: CategoryUseCase
) : ViewModel(), ProductDelegate {
	private val _productList = MutableStateFlow<List<ProductVM>>(listOf())
	val productList: StateFlow<List<ProductVM>> = _productList
	
	suspend fun updateCategory(category: Category) {
		useCase.getProductsByCategory(category = category).collectLatest {
			_productList.emit(convertToPresentationVM(it))
		}
	}
	
	override fun openProduct(product: Product) {
	
	}
	
	private fun convertToPresentationVM(list: List<Product>): List<ProductVM> {
		return list.map {
			ProductVM(it, this)
		}
	}
}