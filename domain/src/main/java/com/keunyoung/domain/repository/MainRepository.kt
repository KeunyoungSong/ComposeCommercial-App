package com.keunyoung.domain.repository

import com.keunyoung.domain.model.Product

interface MainRepository {
	fun getProductList() : List<Product>
}