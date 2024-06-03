package com.keunyoung.domain.repository

import com.keunyoung.domain.model.TestModel

interface TestRepository {
	fun getTestData(): TestModel?
}