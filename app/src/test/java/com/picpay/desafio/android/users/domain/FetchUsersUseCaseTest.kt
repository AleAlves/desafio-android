package com.picpay.desafio.android.users.domain

import com.picpay.desafio.android.users
import com.picpay.desafio.android.users.data.UsersRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.Before
import java.lang.Exception

class FetchUsersUseCaseTest {

    private lateinit var fetchUsersUseCase: FetchUsersUseCase
    private val repository: UsersRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        fetchUsersUseCase = FetchUsersUseCase(repository)
    }

    @Test
    fun `when invoked then should return the user list successfully`() {

        every { repository.fetchUsers() } returns users

        fetchUsersUseCase.invoke(
            onSuccess = {
                assert(it.isNotEmpty())
            }, {})
    }

    @Test
    fun `when invoked then should return the user list empty successfully though`() {

        every { repository.fetchUsers() } returns listOf()

        fetchUsersUseCase.invoke(
            onSuccess = {
                assert(it.isEmpty())
            }, {})
    }

    @Test
    fun `when invoked then should return`() {

        every { repository.fetchUsers() } throws Exception("Error")

        fetchUsersUseCase.invoke(
            {}, onFailure = {
                assert(it == "Error")
            })
    }
}