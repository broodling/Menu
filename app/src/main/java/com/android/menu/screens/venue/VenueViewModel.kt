package com.android.menu.screens.venue

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.domain.GetVenuesUseCase
import com.android.domain.LogOutUserUseCase
import com.android.domain.params.GetVenuesParams
import com.android.menu.viewModel.BaseViewModel
import com.android.model.local.Venue
import com.android.model.networkData.DataError
import com.android.model.networkData.Status
import kotlinx.coroutines.launch

class VenueViewModel(
    val getVenuesUseCase: GetVenuesUseCase,
    val logOutUserUseCase: LogOutUserUseCase
) : BaseViewModel() {

    private val _venues = MutableLiveData<List<Venue>?>()
    val venues = _venues

    private val _isUserLoggedOut = MutableLiveData(false)
    val isUserLoggedOut = _isUserLoggedOut

    fun getVenues() {
        viewModelScope.launch(ioContext()) {
            getVenuesUseCase(GetVenuesParams("44.001783", "21.26907")).collect { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        _loadingVisibility.postValue(false)
                        _venues.postValue(result.data)
                    }

                    Status.LOADING -> {
                        _loadingVisibility.postValue(true)
                    }

                    Status.ERROR -> {
                        _loadingVisibility.postValue(false)
                        _error.postValue(result.throwable?.message?.let { DataError(message = it) })
                    }
                }
            }
        }
    }

    fun logoutUser() {
        viewModelScope.launch {
            logOutUserUseCase().collect { result ->

                when (result.status) {
                    Status.SUCCESS -> {
                        _isUserLoggedOut.postValue(true)
                    }

                    Status.LOADING -> {
                        _loadingVisibility.postValue(true)
                    }

                    Status.ERROR -> {
                        _loadingVisibility.postValue(false)
                        _error.postValue(result.throwable?.localizedMessage?.let { DataError(message = it) })
                    }
                }
            }
        }
    }
}