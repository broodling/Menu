package com.android.model.remote.venue

import com.google.gson.annotations.SerializedName

data class VenuesResponseRemote(
    @SerializedName("status")
    val status: String?,
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val data:VenueDataRemote?
)