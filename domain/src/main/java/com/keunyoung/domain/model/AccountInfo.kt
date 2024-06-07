package com.keunyoung.domain.model

data class AccountInfo(
	val tokenId: String, val name: String, val type: Type
) {
	enum class Type {
		// 다른 로그인 방식 확장 지원
		GOOGLE
	}
}
