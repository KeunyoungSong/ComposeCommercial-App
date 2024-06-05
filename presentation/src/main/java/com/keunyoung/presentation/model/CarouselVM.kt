package com.keunyoung.presentation.model

import com.keunyoung.domain.model.Carousel
import com.keunyoung.domain.model.Product
import com.keunyoung.presentation.delegate.ProductDelegate

class CarouselVM(model: Carousel, private val productDelegate: ProductDelegate) : PresentationVM<Carousel>(model) {
	fun openCarouselProduct(product: Product) {
		productDelegate.openProduct(product = product)
		sendCarouselLog()
	}
	
	private fun sendCarouselLog(){
	
	}
}