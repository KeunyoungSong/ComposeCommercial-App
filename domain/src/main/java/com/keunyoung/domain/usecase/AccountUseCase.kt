package com.keunyoung.domain.usecase

import com.keunyoung.domain.model.AccountInfo
import com.keunyoung.domain.repository.AccountRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AccountUseCase @Inject constructor(
	private val accountRepository: AccountRepository
) {
	
	fun getAccountInfo(): StateFlow<AccountInfo?> {
		return accountRepository.getAccountInfo()
	}
	
	suspend fun signInGoogle(accountInfo: AccountInfo) {
		accountRepository.signInGoogle(accountInfo)
	}
	
	suspend fun signOutGoogle() {
		accountRepository.signOutGoogle()
	}
}