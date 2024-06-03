package com.keunyoung.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.keunyoung.data.db.converter.BasketConverter
import com.keunyoung.domain.model.Category
import com.keunyoung.domain.model.Price
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.Shop

@Entity(tableName = "basket")
@TypeConverters(BasketConverter::class)
data class BasketProductEntity(
	@PrimaryKey val productId: String,
	val productName: String,
	val imageUrl: String,
	val price: Price,
	val category: Category,
	val shop: Shop,
	val isNew: Boolean,
	val isFreeShipping: Boolean,
)

fun BasketProductEntity.toDomainModel(): Product {
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