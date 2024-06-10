package com.keunyoung.domain.usecase

import com.keunyoung.domain.model.PurchaseHistory
import com.keunyoung.domain.repository.PurchaseHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PurchaseHistoryUseCase @Inject constructor(
	private val repository: PurchaseHistoryRepository
) {
	fun getPurchaseHistory(): Flow<List<PurchaseHistory>> {
		return repository.getPurchaseHistory()
	}
}