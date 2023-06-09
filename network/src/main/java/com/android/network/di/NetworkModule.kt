package com.android.network.di

import com.android.network.ApiService
import com.android.network.ServiceInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val OK_HTTP_CLIENT = "OK_HTTP_CLIENT"
const val RETROFIT_CLIENT = "RETROFIT_CLIENT"
const val BASE_URL = "https://api-qa.menu.app/api/"

val networkModule = module {

    // Create Service Interceptor singleton
    single {
        ServiceInterceptor()
    }

    // Create OkHttpClient singleton and add Service Interceptor instance into it
    single(named(OK_HTTP_CLIENT)) {
        OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(get<ServiceInterceptor>()).build()
    }

    // Create Retrofit Client singleton and add OkHttpClient into it
    single(named(RETROFIT_CLIENT)) {
        Retrofit.Builder()
            .client(get(named(OK_HTTP_CLIENT)))
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .build()
    }

    // Add Api Service interface to Retrofit Client
    single { get<Retrofit>(named(RETROFIT_CLIENT)).create(ApiService::class.java) }
}
