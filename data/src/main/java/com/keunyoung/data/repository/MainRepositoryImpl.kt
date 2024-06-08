package com.keunyoung.data.repository

import com.keunyoung.data.datasource.ProductDataSource
import com.keunyoung.data.db.dao.LikeDao
import com.keunyoung.data.db.entity.toLikeProductEntity
import com.keunyoung.domain.model.BaseModel
import com.keunyoung.domain.model.Carousel
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.Ranking
import com.keunyoung.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
	private val dataSource: ProductDataSource,
	private val likeDao: LikeDao
) : MainRepository {
	
	override fun getModelList(): Flow<List<BaseModel>> {
		return dataSource.getHomeComponents().combine(likeDao.getAll()) { homeComponentList, likeList ->
			homeComponentList.map { baseModel -> mappingLike(baseModel, likeList.map { it.productId }) }
		}
	}
	
	override suspend fun likeProduct(product: Product) {
		if (product.isLike) {
			likeDao.delete(product.productId)
		} else {
			likeDao.upsert(product.toLikeProductEntity().copy(isLike = true))
		}
	}
	
	private fun mappingLike(baseModel: BaseModel, likeProductIds: List<String>): BaseModel {
		return when (baseModel) {
			is Product -> {
				updateLikeStatus(baseModel, likeProductIds)
			}
			is Carousel -> {
				baseModel.copy(productList = baseModel.productList.map {
					updateLikeStatus(
						it, likeProductIds = likeProductIds
					)
				})
			}
			is Ranking -> {
				baseModel.copy(productList = baseModel.productList.map {
					updateLikeStatus(
						it, likeProductIds = likeProductIds
					)
				})
			}
			else -> baseModel
		}
	}
	
	private fun updateLikeStatus(product: Product, likeProductIds: List<String>): Product {
		return product.copy(isLike = likeProductIds.contains(product.productId))
	}
}
