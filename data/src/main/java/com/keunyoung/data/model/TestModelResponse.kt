package com.keunyoung.data.model

import com.keunyoung.domain.model.TestModel

class TestModelResponse(val name: String?)

fun TestModelResponse.toDomainModel(): TestModel? {
	if (name != null) {
		return TestModel(name)
	}
	return null
}