package com.keunyoung.presentation.delegate

import com.keunyoung.domain.model.Product

interface ProductDelegate {
	fun openProduct(product: Product)
}