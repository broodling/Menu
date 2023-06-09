package com.android.data.repository

import com.android.data.mappers.UserMapper
import com.android.data.mappers.VenueMapper
import com.android.data.storage.UserPreferencesRepository
import com.android.model.local.Venue
import com.android.model.post.LocationPost
import com.android.model.post.UserPost
import com.android.network.ApiService
import kotlinx.coroutines.flow.first

class MenuDataRepository(
    private val apiService: ApiService,
    private val userMapper: UserMapper,
    private val venueMapper: VenueMapper,
    private val userPreferencesRepository: UserPreferencesRepository
) : MenuRepository {

    override suspend fun saveAccessToken(token: String) {
        userPreferencesRepository.setAccessToken(token)
    }

    override suspend fun isUserLoggedIn() = userPreferencesRepository.accessToken

    override suspend fun loginUser(user: UserPost) = userMapper.map(apiService.loginUser(user))

    override suspend fun getVenues(location: LocationPost): List<Venue> {
        return venueMapper.map(apiService.getVenues(location).data?.venues)
    }

    override suspend fun logoutUser() {
        userPreferencesRepository.clearDataStore()
    }
}