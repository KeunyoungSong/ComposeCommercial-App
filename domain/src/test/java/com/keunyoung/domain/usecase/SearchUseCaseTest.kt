package com.keunyoung.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.keunyoung.domain.model.Category
import com.keunyoung.domain.model.Product
import com.keunyoung.domain.model.SearchFilter
import com.keunyoung.domain.model.SearchKeyword
import com.keunyoung.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@Suppress("NonAsciiCharacters")
@OptIn(ExperimentalCoroutinesApi::class)
class SearchUseCaseTest {
	private lateinit var useCase: SearchUseCase
	
	@Mock
	private lateinit var searchRepository: SearchRepository
	
	@Mock
	private lateinit var topProduct: Product
	
	@Mock
	private lateinit var dressProduct: Product
	
	@Mock
	private lateinit var pantsProduct: Product
	
	private lateinit var searchResponse: List<Product>
	
	private lateinit var autoClosable: AutoCloseable
	
	private val searchKeyword = SearchKeyword("1")
	
	@Before
	fun setup() {
		Dispatchers.setMain(StandardTestDispatcher()) // 코루틴 스코프 범위 설정
		autoClosable = MockitoAnnotations.openMocks(this) // Mock 객체 초기화
		
		`when`(topProduct.category).thenReturn(Category.Top)
		`when`(dressProduct.category).thenReturn(Category.Dress)
		`when`(pantsProduct.category).thenReturn(Category.Pants)
		
		`when`(topProduct.productName).thenReturn("상의1")
		`when`(dressProduct.productName).thenReturn("드레스1")
		`when`(pantsProduct.productName).thenReturn("바지1")
		
		searchResponse = listOf(topProduct, dressProduct, pantsProduct)
		
		useCase = SearchUseCase(searchRepository)
	}
	
	@After
	fun close() {
		Dispatchers.resetMain()  // 초루틴 스코프 범위 복원
		autoClosable.close()  // Mock 객체 해제
	}
	
	@Test
	fun `검색 호출 테스트`() {
		useCase.getSearchKeywords()
		
		verify(searchRepository).getSearchKeywords()
	}
	
	@Test
	fun `상의 필터 검색 테스트`() = runTest {
		`when`(searchRepository.search(searchKeyword)).thenReturn(flow { emit(searchResponse) })
		
		useCase.search(searchKeyword, listOf(SearchFilter.CategoryFilter(listOf(), Category.Top))).test {
			assertThat(awaitItem()).isEqualTo(listOf(topProduct))
			awaitComplete()
		}
	}
	
	@Test
	fun `드레스 필터 검색 테스트`() = runTest {
		`when`(searchRepository.search(searchKeyword)).thenReturn(flow { emit(searchResponse) })
		
		useCase.search(searchKeyword, listOf(SearchFilter.CategoryFilter(listOf(), Category.Dress))).test {
			assertThat(awaitItem()).isEqualTo(listOf(dressProduct))
			awaitComplete()
		}
	}
	
	// search 호출 시 searchKeyword 를 사용하지 않고 "상의"를 사용했기 때문에 에러가 생긴다
	@Test
	fun `에러가 있는 검색어 테스트`() = runTest {
		`when`(searchRepository.search(searchKeyword)).thenReturn(flow { emit(searchResponse) })
		
		useCase.search(SearchKeyword("상의"), listOf()).test {
			assertThat(awaitItem()).isEqualTo(listOf(topProduct))
			awaitComplete()
		}
	}
	
	@Test
	fun `검색어 테스트`() = runTest {
		`when`(searchRepository.search(SearchKeyword("상의"))).thenReturn(flow { emit(searchResponse) })
		
		useCase.search(SearchKeyword("상의"), listOf()).test {
			assertThat(awaitItem()).isEqualTo(listOf(topProduct))
			awaitComplete()
		}
	}
	
	@Test
	fun `검색어 테스트2`() = runTest {
		`when`(searchRepository.search(searchKeyword)).thenReturn(flow { emit(searchResponse) })
		
		useCase.search(searchKeyword, listOf()).test {
			assertThat(awaitItem()).isEqualTo(searchResponse)
			awaitComplete()
		}
	}
}