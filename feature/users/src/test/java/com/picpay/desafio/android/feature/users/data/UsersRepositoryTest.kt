package com.picpay.desafio.android.feature.users.data

import com.picpay.desafio.android.feature.users.users
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test


internal class UsersRepositoryTest {

    private lateinit var repository: UsersRepository

    private val dataSource: UserDataSource = mockk()

    @Before
    fun setup() {
        repository = UsersRepositoryImpl(dataSource)
    }

    @Test
    fun `WHEN fetching the users THEN must returns it successfully`() {

        every { dataSource.fetchUsers() } returns users

        val response = repository.fetchUsers()

        verify { dataSource.fetchUsers() }
        assert(response == users)
    }

    @Test
    fun `WHEN fetching the placeholders THEN must returns it successfully`() {

        every { dataSource.fetchPlaceholders() } returns users

        val response = repository.fetchPlaceholders()

        verify { dataSource.fetchPlaceholders() }
        assert(response == users)
    }
}