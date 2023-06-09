package com.android.model.networkData

data class ResultData<T>(
    val status: Status,
    val data: T? = null,
    val throwable: Throwable? = null,
    val apiCode: String? = null
) {
    companion object {
        fun <T> Success(data: T?): ResultData<T> =
            ResultData(
                status = Status.SUCCESS,
                data = data,
            )

        fun <T> Error(
            throwable: Throwable,
            data: T? = null,
            apiCode: String? = null
        ): ResultData<T> = ResultData(
            status = Status.ERROR,
            data = data,
            throwable = throwable,
            apiCode = apiCode
        )

        fun <T> Loading(data: T? = null): ResultData<T> =
            ResultData(
                status = Status.LOADING,
                data = data
            )
    }
}