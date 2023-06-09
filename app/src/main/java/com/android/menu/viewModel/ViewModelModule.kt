package com.android.menu.viewModel

import com.android.menu.screens.login.LoginViewModel
import com.android.menu.screens.venue.VenueViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { SplashViewModel(get()) }
    factory { LoginViewModel(get()) }
    factory { VenueViewModel(get(), get()) }
}