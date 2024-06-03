package com.keunyoung.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.keunyoung.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: MainUseCase) : ViewModel() {
	val productList = useCase.getProductList()
	
	fun openSearchForm() {
	
	}
}