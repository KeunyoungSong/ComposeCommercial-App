package com.keunyoung.presentation.viewmodel.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.usecase.BasketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
	private val basketUseCase: BasketUseCase
) : ViewModel() {
	val basketProductList = basketUseCase.getBasketProductList()
	
	fun removeBasketProduct(product: Product) {
		viewModelScope.launch {
			basketUseCase.removeBasketProduct(product)
		}
	}
}