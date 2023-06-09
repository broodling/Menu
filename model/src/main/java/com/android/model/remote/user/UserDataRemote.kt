package com.android.model.remote.user

import com.google.gson.annotations.SerializedName

data class UserDataRemote(
    @SerializedName("token")
    val token: TokenRemote?
)