package com.android.data.di

import com.android.data.mappers.UserMapper
import com.android.data.mappers.VenueMapper
import org.koin.dsl.module

val mappersModule = module {
    factory { VenueMapper() }
    factory { UserMapper() }
}