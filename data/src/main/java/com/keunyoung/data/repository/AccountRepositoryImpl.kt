package com.keunyoung.data.repository

import com.keunyoung.data.datasource.PreferenceDatasource
import com.keunyoung.data.db.dao.BasketDao
import com.keunyoung.data.db.dao.LikeDao
import com.keunyoung.domain.model.AccountInfo
import com.keunyoung.domain.repository.AccountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
	private val preferenceDatasource: PreferenceDatasource,
	private val basketDao: BasketDao,
	private val likeDao: LikeDao
) : AccountRepository {
	private val accountInfoFlow = MutableStateFlow(preferenceDatasource.getAccountInfo())
	
	override fun getAccountInfo(): StateFlow<AccountInfo?> {
		return accountInfoFlow
	}
	
	override suspend fun signIn(accountInfo: AccountInfo) {
		preferenceDatasource.putAccountInfo(accountInfo)
		accountInfoFlow.emit(accountInfo)
	}
	
	override suspend fun signOut() {
		preferenceDatasource.removeAccountInfo()
		accountInfoFlow.emit(null)
		basketDao.deleteAll()
		likeDao.deleteAll()
	}
}