package com.android.model.remote.user

import com.google.gson.annotations.SerializedName

data class TokenRemote(
    @SerializedName("value")
    val value: String?
)