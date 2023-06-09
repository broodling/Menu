package com.android.menu.repository

import com.android.model.post.LocationPost
import com.android.model.post.UserPost
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RepositoryTestFailure {

    private lateinit var repository: TestMenuRepository

    @Before
    fun setup() {
        repository = TestMenuRepository()
        repository.setShouldReturnError(true)
    }

    @Test
    fun testIsUserLoggedIn() {
        runBlocking {
            Assert.assertTrue(
                "Result should be empty",
                repository.isUserLoggedIn().first().isEmpty()
            )
        }
    }

    @Test
    fun testLoginUser() {
        runBlocking {
            val testUserPost = UserPost(email = "email@email.com", password = "password")
            Assert.assertTrue(
                "Result should be empty",
                repository.loginUser(testUserPost).isEmpty()
            )
        }
    }

    @Test
    fun testGetVenues() {
        runBlocking {
            val locationPost = LocationPost(latitude = "44.001783", longitude = "21.26907")
            Assert.assertTrue(
                "Result should be empty",
                repository.getVenues(locationPost).isEmpty()
            )
        }
    }
}
