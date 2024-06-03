package com.keunyoung.domain.repository

import com.keunyoung.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface MainRepository {
	fun getProductList() : Flow<List<Product>>
}