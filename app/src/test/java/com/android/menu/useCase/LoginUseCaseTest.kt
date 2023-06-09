package com.android.menu.useCase

import com.android.data.repository.MenuRepository
import com.android.data.storage.UserPreferencesRepository
import com.android.domain.LoginUserUseCase
import com.android.domain.params.LoginUserParams
import com.android.menu.repository.TestMenuRepository
import com.android.model.networkData.ResultData
import com.android.model.post.UserPost
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class LoginUserUseCaseTest {

    private lateinit var loginUserUseCase: LoginUserUseCase
    private lateinit var repository: TestMenuRepository
    private lateinit var userPreferencesRepository: UserPreferencesRepository

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        repository = TestMenuRepository()
        userPreferencesRepository = mock(UserPreferencesRepository::class.java)
        loginUserUseCase = LoginUserUseCase(repository, userPreferencesRepository)
    }

    @Test
    fun `test login user success`() = testDispatcher.runBlockingTest {
        val mockResponse = "UserLoggedIn"
        val params = LoginUserParams("email@example.com", "password123")
        val userPost = UserPost(email = params.email, password = params.password)
        val successResult = ResultData.Success(mockResponse)

        `when`(repository.loginUser(userPost)).thenReturn(mockResponse)
        `when`(userPreferencesRepository.setAccessToken(mockResponse)).thenReturn(Unit)

        val flowResult = loginUserUseCase(params).toList()

        assertTrue(flowResult.isNotEmpty())
        assertEquals(ResultData.Loading(), flowResult[0])
        assertEquals(successResult, flowResult[1])
    }

    @Test
    fun `test login user failure`() = testDispatcher.runBlockingTest {
        // Arrange
        val mockError = "LoginFailed"
        val params = LoginUserParams("email@example.com", "password123")
        val userPost = UserPost(email = params.email, password = params.password)
        val errorResult: ResultData<String> = ResultData.Error(throwable = Throwable(mockError))
        repository.setShouldReturnError(true)

        `when`(repository.loginUser(userPost)).thenReturn("")
        `when`(userPreferencesRepository.setAccessToken("")).thenReturn(Unit)

        // Act
        val flowResult = loginUserUseCase(params).toList()

        // Assert
        assertTrue(flowResult.isNotEmpty())
        assertEquals(ResultData.Loading(), flowResult[0])
        assertEquals(errorResult, flowResult[1])
    }
}