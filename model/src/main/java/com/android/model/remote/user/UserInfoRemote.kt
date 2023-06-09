package com.android.model.remote.user

import com.google.gson.annotations.SerializedName

data class UserInfoRemote(
    @SerializedName("status")
    val status: String?,
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val data: UserDataRemote?
)