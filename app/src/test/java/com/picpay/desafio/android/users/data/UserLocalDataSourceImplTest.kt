package com.picpay.desafio.android.users.data

import com.picpay.desafio.android.users
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test


class UserLocalDataSourceImplTest {

    private lateinit var local: UserLocalDataSource
    private val database: UserDataBase = mockk()

    @Before
    fun setUp() {
        local = UserLocalDataSourceImpl(database)

        every { database.user().fetch() } returns users
        every { database.user().clear() } returns Unit
        every { database.user().update(any()) } returns Unit
    }

    @Test
    fun `when fetching local database then should return a user list`() {
        val data = local.fetch()
        assert(data?.isNotEmpty() == true)
    }

    @Test
    fun `when given a user list from remote then should persist a user list locally`() {
        local.setCache(users)
        val data = local.fetch()
        assert(data?.isNotEmpty() == true)
    }
}