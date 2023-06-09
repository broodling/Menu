package com.android.data.di

import com.android.data.repository.MenuDataRepository
import com.android.data.repository.MenuRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<MenuRepository> {
        MenuDataRepository(get(), get(), get(), get())
    }
}