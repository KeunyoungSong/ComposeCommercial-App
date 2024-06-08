package com.keunyoung.presentation.model

import androidx.navigation.NavHostController
import com.keunyoung.domain.model.Carousel
import com.keunyoung.domain.model.Product
import com.keunyoung.presentation.delegate.ProductDelegate

class CarouselVM(model: Carousel, private val productDelegate: ProductDelegate) :
	PresentationVM<Carousel>(model), ProductDelegate by productDelegate {
	fun openCarouselProduct(navHostController: NavHostController, product: Product) {
		openProduct(navHostController, product)
		sendCarouselLog()
	}
	
	private fun sendCarouselLog() {
	
	}
	
	override fun likeProduct(product: Product){
		productDelegate.likeProduct(product)
	}
}