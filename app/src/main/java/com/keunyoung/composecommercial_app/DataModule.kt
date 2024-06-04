package com.keunyoung.composecommercial_app

import com.keunyoung.data.repository.CategoryRepositoryImpl
import com.keunyoung.data.repository.MainRepositoryImpl
import com.keunyoung.domain.repository.CategoryRepository
import com.keunyoung.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
	
	@Binds
	@Singleton
	fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl) : MainRepository
	
	@Binds
	@Singleton
	fun bindCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl) : CategoryRepository
	
}