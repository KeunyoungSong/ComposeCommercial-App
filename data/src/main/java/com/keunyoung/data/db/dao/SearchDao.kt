package com.keunyoung.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keunyoung.data.db.entity.SearchKeywordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {
	
	@Query("SELECT * FROM search")
	fun getAll() : Flow<List<SearchKeywordEntity>>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(item: SearchKeywordEntity)
}
