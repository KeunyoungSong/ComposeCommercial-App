package com.keunyoung.domain.repository

import com.keunyoung.domain.model.PurchaseHistory
import kotlinx.coroutines.flow.Flow

interface PurchaseHistoryRepository {
	fun getPurchaseHistory(): Flow<List<PurchaseHistory>>
}