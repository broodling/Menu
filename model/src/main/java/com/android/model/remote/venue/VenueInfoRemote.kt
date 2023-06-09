package com.android.model.remote.venue

import com.google.gson.annotations.SerializedName

data class VenueInfoRemote(
    @SerializedName("distance")
    val distance: Double?,
    @SerializedName("venue")
    val venue: VenueRemote?
)
