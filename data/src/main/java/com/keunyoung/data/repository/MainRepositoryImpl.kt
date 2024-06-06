package com.keunyoung.data.repository

import com.keunyoung.data.datasource.ProductDataSource
import com.keunyoung.domain.model.BaseModel
import com.keunyoung.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
	private val dataSource: ProductDataSource
) : MainRepository {
	override fun getModelList(): Flow<List<BaseModel>> = dataSource.getHomeComponents()
}
