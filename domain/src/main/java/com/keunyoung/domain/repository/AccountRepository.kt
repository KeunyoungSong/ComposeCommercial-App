package com.keunyoung.domain.repository

import com.keunyoung.domain.model.AccountInfo
import kotlinx.coroutines.flow.StateFlow

interface AccountRepository {
	fun getAccountInfo(): StateFlow<AccountInfo?>
	
	suspend fun signInGoogle(accountInfo: AccountInfo)
	
	suspend fun signOutGoogle()
	
}