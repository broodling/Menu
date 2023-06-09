package com.android.menu.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.model.networkData.DataError
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    protected val _internetConnectionLost = MutableLiveData<Boolean>()
    val internetConnectionLost = _internetConnectionLost

    protected val _error = MutableLiveData<DataError>()
    val error = _error

    protected val _loadingVisibility = MutableLiveData<Boolean>()
    val loadingVisibility = _loadingVisibility

    protected val ioContext: () -> CoroutineContext = { Dispatchers.IO }

    fun setInternetConnectionLost(isLost: Boolean) {
        _internetConnectionLost.postValue(isLost)
    }

}