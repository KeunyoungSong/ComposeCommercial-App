package com.keunyoung.data.db.entity

import com.keunyoung.domain.model.Category
import com.keunyoung.domain.model.Price
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.Shop

data class PurchaseProductEntity(
	val productId: String,
	val productName: String,
	val imageUrl: String,
	val price: Price,
	val category: Category,
	val shop: Shop,
	val isNew: Boolean,
	val isFreeShipping: Boolean,
)
fun PurchaseProductEntity.toDomainModel(): Product {
	return Product(
		productId = productId,
		productName = productName,
		imageUrl = imageUrl,
		price = price,
		category = category,
		shop = shop,
		isNew = isNew,
		isFreeShipping = isFreeShipping
	)
}