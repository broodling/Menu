package com.android.domain.di

import com.android.domain.GetVenuesUseCase
import com.android.domain.IsUserLoggedInUseCase
import com.android.domain.LogOutUserUseCase
import com.android.domain.LoginUserUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { IsUserLoggedInUseCase(get()) }
    factory { LoginUserUseCase(get(), get()) }
    factory { GetVenuesUseCase(get()) }
    factory { LogOutUserUseCase(get()) }
}