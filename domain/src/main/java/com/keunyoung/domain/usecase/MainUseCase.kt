package com.keunyoung.domain.usecase

import com.keunyoung.domain.model.Product
import com.keunyoung.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainUseCase @Inject constructor(
	private val mainRepository: MainRepository
) {
	fun getProductList(): Flow<List<Product>> {
		return mainRepository.getProductList()
	}
}