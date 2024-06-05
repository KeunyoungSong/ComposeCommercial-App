package com.keunyoung.presentation.model

import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.Ranking
import com.keunyoung.presentation.delegate.ProductDelegate

class RankingVM(model: Ranking, private val productDelegate: ProductDelegate) : PresentationVM(model) {
	fun openRankingProduct(product: Product) {
		productDelegate.openProduct(product)
		sendRankingLog()
		// +@
	}
	
	private fun sendRankingLog() {
	
	}
}