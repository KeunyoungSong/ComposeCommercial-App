package com.keunyoung.presentation.model

import androidx.navigation.NavHostController
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.Ranking
import com.keunyoung.presentation.delegate.ProductDelegate

class RankingVM(model: Ranking, private val productDelegate: ProductDelegate) : PresentationVM<Ranking>(model) {
	fun openRankingProduct(navHostController: NavHostController, product: Product) {
		productDelegate.openProduct(navHostController, product)
		sendRankingLog()
		// +@
	}
	
	private fun sendRankingLog() {
	
	}
}