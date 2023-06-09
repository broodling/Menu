package com.android.model.remote.venue

import com.google.gson.annotations.SerializedName

data class VenueRemote(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("welcome_message")
    val welcomeMessage: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("is_open")
    val isOpen: Boolean?,
    @SerializedName("image")
    val image: ImageRemote?
)
