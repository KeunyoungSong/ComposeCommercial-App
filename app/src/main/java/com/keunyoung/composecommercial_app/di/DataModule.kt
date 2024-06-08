package com.keunyoung.composecommercial_app.di

import com.keunyoung.data.repository.AccountRepositoryImpl
import com.keunyoung.data.repository.CategoryRepositoryImpl
import com.keunyoung.data.repository.LikeRepositoryImpl
import com.keunyoung.data.repository.MainRepositoryImpl
import com.keunyoung.data.repository.ProductDetailRepositoryImpl
import com.keunyoung.data.repository.SearchRepositoryImpl
import com.keunyoung.domain.repository.AccountRepository
import com.keunyoung.domain.repository.CategoryRepository
import com.keunyoung.domain.repository.LikeRepository
import com.keunyoung.domain.repository.MainRepository
import com.keunyoung.domain.repository.ProductDetailRepository
import com.keunyoung.domain.repository.SearchRepository
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
	fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
	
	@Binds
	@Singleton
	fun bindCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository
	
	@Binds
	@Singleton
	fun bindProductDetailRepository(productDetailRepositoryImpl: ProductDetailRepositoryImpl): ProductDetailRepository
	
	@Binds
	@Singleton
	fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
	
	@Binds
	@Singleton
	fun bindAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository
	
	@Binds
	@Singleton
	fun bindLikeRepository(likeRepositoryImpl: LikeRepositoryImpl): LikeRepository
}