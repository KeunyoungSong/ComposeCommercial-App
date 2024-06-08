package com.keunyoung.domain.repository

import com.keunyoung.domain.model.BaseModel
import com.keunyoung.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface MainRepository {
	fun getModelList() : Flow<List<BaseModel>>
	
	suspend fun likeProduct(product: Product)
}