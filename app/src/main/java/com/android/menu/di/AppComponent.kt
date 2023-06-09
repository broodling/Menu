package com.android.menu.di

import com.android.data.di.dataStoreModule
import com.android.data.di.mappersModule
import com.android.data.di.repositoryModule
import com.android.domain.di.useCaseModule
import com.android.menu.viewModel.viewModelModule
import com.android.network.di.networkModule

val appComponent = listOf(
    repositoryModule,
    dataStoreModule,
    mappersModule,
    networkModule,
    useCaseModule,
    viewModelModule
)