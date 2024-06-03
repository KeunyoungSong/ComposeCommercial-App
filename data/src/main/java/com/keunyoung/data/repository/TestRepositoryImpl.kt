package com.keunyoung.data.repository

import com.keunyoung.data.datasource.TestDataSource
import com.keunyoung.data.model.toDomainModel
import com.keunyoung.domain.model.TestModel
import com.keunyoung.domain.repository.TestRepository

class TestRepositoryImpl(private val dataSource: TestDataSource) : TestRepository {
	override fun getTestData(): TestModel? {
		return dataSource.getTestModelResponse().toDomainModel()
	}
}