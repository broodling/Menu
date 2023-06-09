package com.android.menu.screens.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.android.menu.databinding.ActivityLoginBinding
import com.android.menu.helpers.addOnTextChangeListener
import com.android.menu.screens.BaseActivity
import com.android.menu.screens.venue.VenueActivity
import com.android.menu.ui.showSimpleDialog
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    override val viewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObservers()
        setupViews()
    }

    private fun setObservers() {
        viewModel.loadingVisibility.observe(this) {
            binding.groupProgress.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.error.observe(this) {
            showSimpleDialog("Error", it.message, this)
        }

        viewModel.emailAndPassword.observe(this) { (email, password) ->
            binding.buttonSignIn.isEnabled = !(email.isNullOrEmpty() && password.isNullOrEmpty())
        }

        viewModel.loginSuccess.observe(this) {
            if (it) startVenueActivity()
        }
    }

    private fun setupViews() {
        binding.editTextEmail.addOnTextChangeListener {
            viewModel.setEmail(it)
        }
        binding.editTextPassword.addOnTextChangeListener {
            viewModel.setPassword(it)
        }

        binding.buttonSignIn.setOnClickListener {
            viewModel.loginUser()
        }
    }

    private fun startVenueActivity() {
        startActivity(Intent(this, VenueActivity::class.java))
        finish()
    }

}