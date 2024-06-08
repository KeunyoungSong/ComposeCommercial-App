package com.keunyoung.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keunyoung.data.db.entity.LikeProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LikeDao {
	
	@Query("SELECT * FROM like")
	fun getAll(): Flow<List<LikeProductEntity>>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(item: LikeProductEntity)
	
	@Query("DELETE FROM like WHERE productId=:id")
	suspend fun delete(id: String)
}