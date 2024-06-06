package com.keunyoung.composecommercial_app.di

import android.content.Context
import androidx.room.Room
import com.keunyoung.data.db.ApplicationDatabase
import com.keunyoung.data.db.dao.SearchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
	@Provides
	@Singleton
	fun provideDatabase(
		@ApplicationContext context: Context
	): ApplicationDatabase {
		return Room.databaseBuilder(
			context.applicationContext, ApplicationDatabase::class.java, ApplicationDatabase.DB_NAME
		).fallbackToDestructiveMigration().build()
	}
	
	@Provides
	@Singleton
	fun provideSearchDao(database: ApplicationDatabase): SearchDao {
		return database.searchDao()
	}
}





