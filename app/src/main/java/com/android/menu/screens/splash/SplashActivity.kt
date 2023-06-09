package com.android.menu.screens.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.android.menu.R
import com.android.menu.screens.login.LoginActivity
import com.android.menu.screens.venue.VenueActivity
import com.android.menu.viewModel.SplashViewModel
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setObservers()
        checkIfUserIsLoggedIn()
    }

    private fun setObservers() {
        splashViewModel.isUserLoggedIn.observe(this) { accessToken ->
            redirectToProperActivity(accessToken.isEmpty())
        }
    }

    private fun checkIfUserIsLoggedIn() {
        splashViewModel.isUserLoggedIn()
    }

    private fun redirectToProperActivity(needToLogin: Boolean) {
        object : CountDownTimer(1000, 1000) {
            override fun onTick(p0: Long) {}
            override fun onFinish() {

                if (needToLogin) navigateToLoginActivity()
                else navigateToVenueActivity()
            }
        }.start()
    }

    private fun navigateToLoginActivity() {
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()

    }

    private fun navigateToVenueActivity() {
        startActivity(Intent(this@SplashActivity, VenueActivity::class.java))
        finish()
    }
}