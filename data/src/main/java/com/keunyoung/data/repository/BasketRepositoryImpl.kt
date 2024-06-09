package com.keunyoung.data.repository

import com.keunyoung.data.db.dao.BasketDao
import com.keunyoung.data.db.entity.toDomainModel
import com.keunyoung.domain.model.BasketProduct
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
	private val basketDao: BasketDao
) : BasketRepository{
	override fun getBasketProducts(): Flow<List<BasketProduct>> {
		return basketDao.getAll().map { list ->
			list.map { BasketProduct(it.toDomainModel(), it.count) }
		}
	}
	
	override suspend fun removeBasketProduct(product: Product) {
		return basketDao.delete(product.productId)
	}
}