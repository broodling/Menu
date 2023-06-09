package com.android.network

import com.android.model.post.LocationPost
import com.android.model.post.UserPost
import com.android.model.remote.user.UserInfoRemote
import com.android.model.remote.venue.VenuesResponseRemote
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("customers/login")
    suspend fun loginUser(
        @Body user: UserPost
    ): UserInfoRemote

    @POST("directory/search")
    suspend fun getVenues(
        @Body location: LocationPost
    ): VenuesResponseRemote
}