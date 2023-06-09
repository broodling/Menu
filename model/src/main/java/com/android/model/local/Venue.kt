package com.android.model.local

import java.io.Serializable

data class Venue(
    val id: Int,
    val name: String,
    val description: String,
    val welcomeMessage: String,
    val address: String,
    val city: String,
    val isOpen: Boolean,
    val image: String,
    val distance: Double,
) : Serializable
