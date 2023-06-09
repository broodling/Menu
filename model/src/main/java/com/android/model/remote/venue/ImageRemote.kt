package com.android.model.remote.venue

import com.google.gson.annotations.SerializedName

data class ImageRemote(
    @SerializedName("thumbnail_medium")
    val thumbnailMedium: String?
)
