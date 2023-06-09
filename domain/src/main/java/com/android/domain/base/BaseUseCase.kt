package com.android.domain.base

import com.android.model.networkData.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

abstract class BaseUseCase<P : BaseParams, T> {

    fun asFlowWithStates(
        apiCode: String? = null,
        onRequest: suspend () -> T,
    ): Flow<ResultData<T>> = flow {
        val data = onRequest()
        emit(ResultData.Success(data))
    }.onStart {
        emit(ResultData.Loading())
    }.catch {
        emit(ResultData.Error(throwable = it, apiCode = apiCode))
    }

    fun asFlow(
        dataProvider: suspend () -> T,
    ): Flow<T> = flow {
        emit(dataProvider())
    }
}