package com.android.network

import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("application", "mobile-application")
            .header("Content-Type", "application/json")
            .header("Device-UUID", "123456")
            .header("Api-Version", "3.7.0")
            .build()
        return chain.proceed(request)
    }
}