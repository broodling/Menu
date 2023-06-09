package com.android.menu.repository

import com.android.data.repository.MenuRepository
import com.android.model.local.Venue
import com.android.model.post.LocationPost
import com.android.model.post.UserPost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TestMenuRepository : MenuRepository {

    private var shouldReturnError = false

    fun setShouldReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun isUserLoggedIn(): Flow<String> =
        if (shouldReturnError) flowOf("") else flowOf("testAccessToken")

    override suspend fun loginUser(user: UserPost) = if (shouldReturnError)  "" else "UserLoggedIn"

    override suspend fun saveAccessToken(token: String) {
    }

    override suspend fun getVenues(location: LocationPost): List<Venue> =
        if (shouldReturnError) emptyList() else createSuccessList()

    override suspend fun logoutUser() {
    }

    private fun createSuccessList(): List<Venue> {
        return listOf(
            Venue(
                0,
                "Venue",
                "Description",
                "Welcome message",
                "Address",
                "City",
                true,
                "image",
                2.12
            )
        )
    }
}