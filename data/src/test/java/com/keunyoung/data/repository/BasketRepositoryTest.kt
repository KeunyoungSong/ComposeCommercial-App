package com.keunyoung.data.repository

import com.google.common.truth.Truth.assertThat
import com.keunyoung.data.db.ApplicationDatabase
import com.keunyoung.data.db.dao.BasketDao
import com.keunyoung.data.db.dao.PurchaseHistoryDao
import com.keunyoung.data.db.entity.BasketProductEntity
import com.keunyoung.domain.model.BasketProduct
import com.keunyoung.domain.model.Category
import com.keunyoung.domain.model.Price
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.SalesStatus
import com.keunyoung.domain.model.Shop
import com.keunyoung.domain.repository.BasketRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@Suppress("NonAsciiCharacters")
@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class, manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class BasketRepositoryTest {
	
	@get:Rule
	val hiltRule = HiltAndroidRule(this)
	
	@Inject
	lateinit var database: ApplicationDatabase
	
	lateinit var basketDao: BasketDao
	lateinit var purchaseHistoryDao: PurchaseHistoryDao
	lateinit var basketRepository: BasketRepository
	
	private val price = Price(10000, 10000, SalesStatus.ON_SALE)
	private val shop = Shop("0", "0", "")
	private val category = Category.Top
	
	private val basketProductEntity =
		BasketProductEntity("", "", "", price, category, shop, false, false, false, 1)
	
	private val basketProduct = BasketProduct(
		Product("", "", "", price, category, shop, false, false, false), 1
	)
	
	@Before
	fun setup() {
		Dispatchers.setMain(StandardTestDispatcher())
		hiltRule.inject()
		
		basketDao = database.basketDao()
		purchaseHistoryDao = database.purchaseHistoryDao()
		basketRepository = BasketRepositoryImpl(basketDao, purchaseHistoryDao)
		
	}
	
	@After
	fun close() {
		Dispatchers.resetMain()
		database.close()
	}
	
	@Test
	fun `결제 테스트`() = runTest {
		basketDao.upsert(basketProductEntity)
		basketRepository.checkoutBasket(listOf(basketProduct))
		
		assertThat(purchaseHistoryDao.get("1")).isNotNull()
		assertThat(basketDao.get(basketProduct.product.productId)).isNull()
	}
	
}