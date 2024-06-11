package com.keunyoung.presentation.viewmodel.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keunyoung.domain.model.BasketProduct
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.usecase.BasketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
	private val basketUseCase: BasketUseCase
) : ViewModel() {
	val basketProductList = basketUseCase.getBasketProductList()
	
	private val _eventFlow = MutableSharedFlow<BasketEvent>()
	val eventFlow: SharedFlow<BasketEvent> = _eventFlow
	
	private val _actionFlow = MutableStateFlow<BasketAction?>(null)
	val actionFlow: SharedFlow<BasketAction?> = _actionFlow
	
	
	fun dispatch(action: BasketAction){
		when(action){
			is BasketAction.CheckoutBasket -> checkoutBasket(action.productList)
			is BasketAction.RemoveProduct -> removeBasketProduct(action.product)
		}
	}
	
	private fun removeBasketProduct(product: Product) {
		viewModelScope.launch {
			basketUseCase.removeBasketProduct(product)
		}
	}
	
	private fun checkoutBasket(productList: List<BasketProduct>) {
		viewModelScope.launch {
			basketUseCase.checkoutBasket(productList)
		}
	}
}

// ViewModel -> Screen
sealed class BasketEvent {
	object ShowSnackBar : BasketEvent()
}

// Screen -> ViewModel
sealed class BasketAction {
	data class RemoveProduct(val product: Product) : BasketAction()
	data class CheckoutBasket(val productList: List<BasketProduct>) : BasketAction()
}