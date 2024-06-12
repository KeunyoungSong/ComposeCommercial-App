package com.keunyoung.data.repository

import com.keunyoung.data.datasource.ProductDataSource
import com.keunyoung.data.db.dao.BasketDao
import com.keunyoung.data.db.entity.toBasketProductEntity
import com.keunyoung.domain.model.Banner
import com.keunyoung.domain.model.BannerList
import com.keunyoung.domain.model.Carousel
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.Ranking
import com.keunyoung.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(
	private val dataSource: ProductDataSource, private val basketDao: BasketDao
) : ProductDetailRepository {
	override fun getProductDetail(productId: String): Flow<Product?> {
		return dataSource.getHomeComponentList().map { productList ->
			productList.filterIsInstance<Product>().firstOrNull() { it.productId == productId }
		}
	}
	
	override suspend fun addProduct(product: Product) {
		val existingProduct = basketDao.get(product.productId)
		if (existingProduct == null) {
			basketDao.upsert(product.toBasketProductEntity())
		} else {
			basketDao.upsert(existingProduct.copy(count = existingProduct.count + 1))
		}
	}
}