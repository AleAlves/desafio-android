package com.picpay.desafio.android.feature.users.data

import com.picpay.desafio.android.feature.users.users
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

internal class UserRemoteDataSourceTest {

    private lateinit var remoteDataSource: UserRemoteDataSource

    private val service: PicPayService = mockk()

    @Before
    fun setup() {
        remoteDataSource = UserRemoteDataSourceImpl(service)
    }

    @Test
    fun `WHEN fetching the users THEN must returns it successfully`() {

        every { remoteDataSource.fetchUsers() } returns users

        val response = remoteDataSource.fetchUsers()

        verify { remoteDataSource.fetchUsers() }
        assert(response == users)
    }

}