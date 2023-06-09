package com.android.menu.repository

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RepositoryTestSuccess {

    private lateinit var repository: TestMenuRepository

    @Before
    fun setup() {
        repository = TestMenuRepository()
    }

    @Test
    fun testIsUserLoggedIn() {
        runBlocking {
            assertTrue("Result is empty", repository.isUserLoggedIn().first().isNotEmpty())
        }
    }
}