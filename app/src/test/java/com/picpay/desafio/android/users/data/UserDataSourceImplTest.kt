package com.picpay.desafio.android.users.data

import com.picpay.desafio.android.users
import org.junit.Test

import io.mockk.*
import org.junit.Before

class UserDataSourceImplTest {

    private lateinit var userDataSource: UserDataSource
    private val remote: UserRemoteDataSource = mockk()
    private val local: UserLocalDataSource = mockk()

    @Before
    fun setup() {
        userDataSource = UserDataSourceImpl(remote, local)

        every { local.setCache(any()) } returns Unit
    }

    @Test
    fun `given a requisition to fetch users then return it from local source successfully`() {
        every { local.fetch() } returns users
        every { remote.fetch() } returns null

        val data = userDataSource.fetch()

        assert(data?.isNotEmpty() == true)
    }

    @Test
    fun `given a requisition to fetch users then return it from remote source successfully`() {
        every { local.fetch() } returns null
        every { remote.fetch() } returns users

        val data = userDataSource.fetch()

        assert(data?.isNotEmpty() == true)
    }

    @Test
    fun `given a requisition to fetch users then return it empty`() {
        every { local.fetch() } returns null
        every { remote.fetch() } returns null

        val data = userDataSource.fetch()

        assert(data?.isEmpty() == true)
    }

}