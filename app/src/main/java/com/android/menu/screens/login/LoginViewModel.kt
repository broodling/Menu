package com.android.menu.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.domain.LoginUserUseCase
import com.android.domain.params.LoginUserParams
import com.android.menu.helpers.safeLet
import com.android.menu.viewModel.BaseViewModel
import com.android.model.networkData.DataError
import com.android.model.networkData.Status
import kotlinx.coroutines.launch

class LoginViewModel(
    val loginUserUseCase: LoginUserUseCase,
) : BaseViewModel() {

    private val _email = MutableLiveData<String?>()
    private val _password = MutableLiveData<String?>()
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess = _loginSuccess

    val emailAndPassword: LiveData<Pair<String?, String?>> =
        object : MediatorLiveData<Pair<String?, String?>>() {
            var email: String? = null
            var password: String? = null

            init {
                addSource(_email) { email ->
                    this.email = email
                    password?.let { value = email to it }
                }
                addSource(_password) { password ->
                    this.password = password
                    email?.let { value = it to password }
                }
            }
        }

    fun setEmail(email: String?) {
        _email.postValue(email)
    }

    fun setPassword(password: String?) {
        _password.postValue(password)
    }

    fun loginUser() {
        viewModelScope.launch(ioContext()) {
            safeLet(_email.value, _password.value) { email, password ->
                loginUserUseCase(
                    LoginUserParams(
                        email = email,
                        password = password
                    )
                ).collect { result ->
                    when (result.status) {
                        Status.SUCCESS -> {
                            _loginSuccess.postValue(true)
                        }

                        Status.LOADING -> {
                            _loadingVisibility.postValue(true)
                        }

                        Status.ERROR -> {
                            _loadingVisibility.postValue(false)
                            _error.postValue(result.throwable?.let { DataError(message = it.toString()) })
                        }
                    }
                }
            }
        }
    }
}
