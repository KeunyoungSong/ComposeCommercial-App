package com.keunyoung.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keunyoung.data.db.entity.BasketProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {
	
	@Query("SELECT * FROM basket")
	fun getAll() : Flow<List<BasketProductEntity>>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(item: BasketProductEntity)
	
	@Query("SELECT * From basket WHERE productId=:id")
	suspend fun get(id: String): BasketProductEntity?
	
	@Query("DELETE FROM basket WHERE productId=:id")
	suspend fun delete(id: String)
	
	@Query("DELETE FROM basket")
	suspend fun deleteAll()

}