package com.android.data.repository

import com.android.model.local.Venue
import com.android.model.post.LocationPost
import com.android.model.post.UserPost
import kotlinx.coroutines.flow.Flow

interface MenuRepository {

    suspend fun isUserLoggedIn(): Flow<String>
    suspend fun loginUser(user: UserPost): String
    suspend fun saveAccessToken(token: String)
    suspend fun getVenues(location: LocationPost): List<Venue>
    suspend fun logoutUser()
}