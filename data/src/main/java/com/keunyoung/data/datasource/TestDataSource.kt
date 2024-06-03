package com.keunyoung.data.datasource

import com.keunyoung.data.model.TestModelResponse

class TestDataSource {
	fun getTestModelResponse(): TestModelResponse {
		return TestModelResponse("response")
	}
}