package com.keunyoung.presentation.viewmodel.product_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.usecase.ProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
	private val useCase: ProductDetailUseCase
) : ViewModel() {
	private val _product = MutableStateFlow<Product?>(null)
	val product: StateFlow<Product?> = _product
	
	fun updateProduct(productId: String) {
		viewModelScope.launch {
			useCase.getProductDetail(productId).collectLatest {
				Log.d("song", "id: $productId, product: $it")
				_product.emit(it)
			}
		}
	}
	
	fun addBasket(product: Product?) {
		product ?: return
		viewModelScope.launch {
			useCase.addBasket(product)
		}
	}
}