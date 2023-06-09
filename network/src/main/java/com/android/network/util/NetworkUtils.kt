package com.android.network.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest

private val networkRequest: NetworkRequest =
    NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).build()

fun buildConnectivityManager(
    context: Context, networkCallback: ConnectivityManager.NetworkCallback
): ConnectivityManager {
    val connectivityManager =
        context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
    connectivityManager.requestNetwork(networkRequest, networkCallback)
    return connectivityManager
}