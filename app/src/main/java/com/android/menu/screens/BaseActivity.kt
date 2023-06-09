package com.android.menu.screens

import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.menu.R
import com.android.menu.ui.showSimpleDialog
import com.android.menu.viewModel.BaseViewModel
import com.android.network.util.buildConnectivityManager
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {

    protected open val viewModel: BaseViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addObservers()
        addConnectivityManager()
    }

    private fun addObservers() {
        viewModel.internetConnectionLost.observe(this) { isLost ->
            if (isLost)
                showSimpleDialog(
                    getString(R.string.internet_connection_lost),
                    getString(R.string.please_turn_on_internet_connection),
                    this
                )
        }
    }

    private fun addConnectivityManager() {
        buildConnectivityManager(this, object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                viewModel.setInternetConnectionLost(false)
            }

            // Lost network connection
            override fun onLost(network: Network) {
                super.onLost(network)
                viewModel.setInternetConnectionLost(true)
            }
        })
    }

}