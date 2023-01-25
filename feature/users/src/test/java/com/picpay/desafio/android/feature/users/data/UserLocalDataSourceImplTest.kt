package com.picpay.desafio.android.feature.users.data

import com.google.gson.reflect.TypeToken
import com.picpay.desafio.android.feature.users.data.model.UserModel
import com.picpay.desafio.android.feature.users.user
import com.picpay.desafio.android.feature.users.users
import com.picpay.desafio.core.util.CacheProvider
import io.mockk.*
import org.junit.Before
import org.junit.Test

internal class UserLocalDataSourceImplTest {

    private lateinit var localDataSource: UserLocalDataSource

    private val cacheProvider: CacheProvider = mockk()

    @Before
    fun setup() {
        localDataSource = UserLocalDataSourceImpl(cacheProvider, users)
    }

    @Test
    fun `WHEN fetching the users from cache THEN must returns it successfully`() {

        every { cacheProvider.retrieve<List<UserModel>>(any(), any()) } returns users

        val response = localDataSource.fetchUsers()

        verify { cacheProvider.retrieve("users", object : TypeToken<List<UserModel>>() {}.type) }
        assert(response == users)
    }

    @Test
    fun `WHEN fetching the placeholders from cache THEN must returns it successfully`() {

        val response = localDataSource.fetchPlaceholders()

        assert(response == users)
    }

    @Test
    fun `WHEN fetching the placeholders from cache`() {

        every { cacheProvider.stash("users", users) } just Runs

        localDataSource.stash(users)

        verify { cacheProvider.stash("users", users) }
    }
}