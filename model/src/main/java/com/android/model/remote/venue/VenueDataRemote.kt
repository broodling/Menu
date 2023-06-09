package com.android.model.remote.venue

import com.google.gson.annotations.SerializedName

data class VenueDataRemote(
    @SerializedName("venues")
    val venues: List<VenueInfoRemote>
)
