package com.keunyoung.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keunyoung.data.db.dao.BasketDao
import com.keunyoung.data.db.dao.LikeDao
import com.keunyoung.data.db.dao.PurchaseDao
import com.keunyoung.data.db.dao.PurchaseHistoryDao
import com.keunyoung.data.db.dao.SearchDao
import com.keunyoung.data.db.entity.BasketProductEntity
import com.keunyoung.data.db.entity.LikeProductEntity
import com.keunyoung.data.db.entity.PurchaseHistoryEntity
import com.keunyoung.data.db.entity.PurchaseProductEntity
import com.keunyoung.data.db.entity.SearchKeywordEntity

@Database(
	entities = [PurchaseProductEntity::class, LikeProductEntity::class, BasketProductEntity::class, SearchKeywordEntity::class, PurchaseHistoryEntity::class],
	version = 4
)
abstract class ApplicationDatabase : RoomDatabase() {
	companion object {
		const val DB_NAME = "ApplicationDataBase.db"
	}
	
	abstract fun purchaseDao(): PurchaseDao
	abstract fun likeDao(): LikeDao
	abstract fun basketDao(): BasketDao
	abstract fun searchDao(): SearchDao
	abstract fun purchaseHistoryDao(): PurchaseHistoryDao
}