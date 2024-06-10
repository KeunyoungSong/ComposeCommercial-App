package com.keunyoung.data.repository

import com.keunyoung.data.db.dao.PurchaseHistoryDao
import com.keunyoung.data.db.entity.toDomain
import com.keunyoung.domain.model.PurchaseHistory
import com.keunyoung.domain.repository.PurchaseHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PurchaseHistoryImpl @Inject constructor(
	private val purchaseHistoryDao: PurchaseHistoryDao
) : PurchaseHistoryRepository {
	override fun getPurchaseHistory(): Flow<List<PurchaseHistory>> {
		return purchaseHistoryDao.getAll().map { list ->
			list.map { it.toDomain() }
		}
	}
}