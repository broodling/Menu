package com.android.menu

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.android.domain.IsUserLoggedInUseCase
import com.android.menu.repository.TestMenuRepository
import com.android.menu.viewModel.SplashViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class SplashViewModelTest {

    private lateinit var isUserLoggedInUseCase: IsUserLoggedInUseCase
    private lateinit var repository: TestMenuRepository
    private lateinit var splashViewModel: SplashViewModel

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = TestMenuRepository()
        isUserLoggedInUseCase = IsUserLoggedInUseCase(repository)
        splashViewModel = SplashViewModel(isUserLoggedInUseCase)
    }

    @After
    fun cleanup(){
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testIsUserLoggedInError() = testScope.runTest {
        val observer = mock(Observer::class.java) as Observer<String>
        repository.setShouldReturnError(true)

        val observedDataList = mutableListOf<String>()
        splashViewModel.isUserLoggedIn.observeForever {
            observedDataList.add(it)
        }

        splashViewModel.isUserLoggedIn()
        advanceUntilIdle()

        val observedData = observedDataList.firstOrNull()

        assertNotNull(observedData)
        assertIs<String>(observedData)
        assertTrue(observedData.isEmpty())

        splashViewModel.isUserLoggedIn.removeObserver(observer)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testIsUserLoggedInSuccess() = testScope.runTest {
        val observer = mock(Observer::class.java) as Observer<String>

        val observedDataList = mutableListOf<String>()
        splashViewModel.isUserLoggedIn.observeForever {
            observedDataList.add(it)
        }
        splashViewModel.isUserLoggedIn()
        advanceUntilIdle()

        val observedData = observedDataList.firstOrNull()
        assertNotNull(observedData)
        assertIs<String>(observedData)
        assertTrue(observedData.isNotEmpty())

        splashViewModel.isUserLoggedIn.removeObserver(observer)
    }

    @Test
    fun testisUserLoggedInUseCaseInjection() {

        // Verify that the correct use case is injected
        assertEquals(isUserLoggedInUseCase, splashViewModel.isUserLoggedInUseCase)
    }

    private fun advanceUntilIdle() {
        testDispatcher.advanceUntilIdle()
    }
}
