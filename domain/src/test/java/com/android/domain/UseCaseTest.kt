package com.android.domain

import kotlinx.coroutines.flow.Flow
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class UseCaseTest {

    private lateinit var isUserLoggedInUseCase: IsUserLoggedInUseCase
    private lateinit var logoutUserUseCase: LogOutUserUseCase
    private lateinit var loginUserUseCase: LoginUserUseCase
    private lateinit var getVenuesUseCase: GetVenuesUseCase

    @Before
    fun setup() {
        isUserLoggedInUseCase = mock(IsUserLoggedInUseCase::class.java)
        logoutUserUseCase = mock(LogOutUserUseCase::class.java)
        loginUserUseCase = mock(LoginUserUseCase::class.java)
        getVenuesUseCase = mock(getVenuesUseCase::class.java)
    }

    @Test
    fun testUseCases() {
       Assert.assertTrue(isUserLoggedInUseCase.invoke() is Flow<String>)
//        isUserLoggedInUseCase.invoke()
    }
}