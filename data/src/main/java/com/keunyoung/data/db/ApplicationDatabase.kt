package com.keunyoung.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keunyoung.data.db.dao.BasketDao
import com.keunyoung.data.db.dao.LikeDao
import com.keunyoung.data.db.dao.PurchaseDao
import com.keunyoung.data.db.entity.BasketProductEntity
import com.keunyoung.data.db.entity.LikeProductEntity
import com.keunyoung.data.db.entity.PurchaseProductEntity

@Database(
	entities = [PurchaseProductEntity::class, LikeProductEntity::class, BasketProductEntity::class],
	version = 1
)
abstract class ApplicationDatabase : RoomDatabase() {
	companion object {
		const val DB_NAME = "ApplicationDataBase.db"
	}
	
	abstract fun purchaseDao(): PurchaseDao
	abstract fun likeDao(): LikeDao
	abstract fun basketDao(): BasketDao
}