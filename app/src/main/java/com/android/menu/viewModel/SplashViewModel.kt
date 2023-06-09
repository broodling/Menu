package com.android.menu.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.domain.IsUserLoggedInUseCase
import kotlinx.coroutines.launch

class SplashViewModel(val isUserLoggedInUseCase: IsUserLoggedInUseCase) : BaseViewModel() {

    private val _isUserLoggedIn = MutableLiveData<String>()
    val isUserLoggedIn = _isUserLoggedIn

    fun isUserLoggedIn() {
        viewModelScope.launch {
            isUserLoggedInUseCase().collect {
                isUserLoggedIn.postValue(it)
            }
        }
    }
}