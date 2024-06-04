package com.keunyoung.domain.repository

import com.keunyoung.domain.model.BaseModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
	fun getModelList() : Flow<List<BaseModel>>
}