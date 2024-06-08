package com.keunyoung.data.repository

import com.keunyoung.data.db.dao.LikeDao
import com.keunyoung.data.db.entity.toDomainModel
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
	private val likeDao : LikeDao
): LikeRepository {
	override fun getLikeProduct(): Flow<List<Product>> {
		return likeDao.getAll().map { likeProductEntityList ->
			likeProductEntityList.map { it.toDomainModel() }
		}
	}
}