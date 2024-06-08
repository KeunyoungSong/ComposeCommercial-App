package com.keunyoung.domain.usecase

import com.keunyoung.domain.model.Product
import com.keunyoung.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LikeUseCase @Inject constructor(
	private val repository: LikeRepository
) {
	fun getLikeProducts(): Flow<List<Product>> {
		return repository.getLikeProduct()
	}
}