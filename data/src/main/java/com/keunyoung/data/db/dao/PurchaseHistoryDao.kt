package com.keunyoung.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keunyoung.data.db.entity.PurchaseHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseHistoryDao {
	
	@Query("SELECT * FROM history")
	fun getAll() : Flow<List<PurchaseHistoryEntity>>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(item: PurchaseHistoryEntity)
	
//	@Query("SELECT * From history WHERE productId=:id")
//	suspend fun get(id: String): PurchaseHistoryEntity?
//
//	@Query("DELETE FROM history WHERE productId=:id")
//	suspend fun delete(id: String)
	
	@Query("DELETE FROM history")
	suspend fun deleteAll()
}