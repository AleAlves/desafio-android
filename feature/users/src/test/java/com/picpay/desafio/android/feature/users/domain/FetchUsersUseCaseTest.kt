package com.picpay.desafio.android.feature.users.domain

import android.content.Context
import com.picpay.desafio.android.feature.users.data.UsersRepository
import com.picpay.desafio.android.feature.users.users
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test


class FetchUsersUseCaseTest {

    private lateinit var fetchUsersUseCase: FetchUsersUseCase
    private val repository: UsersRepository = mockk(relaxed = true)
    private val context: Context = mockk(relaxed = true)

    @Before
    fun setup() {
        fetchUsersUseCase = FetchUsersUseCase(context, repository)
    }

    @Test
    fun `WHEN fetching the users THEN must return it successfully`() {

        every { repository.fetchUsers() } returns users

        fetchUsersUseCase.invoke(
            onSuccess = {
                assert(it.isNotEmpty())
            })
    }

    @Test
    fun `WHEN fetching the users BUT without registers THEN must return an empty list`() {

        every { repository.fetchUsers() } returns listOf()

        fetchUsersUseCase.invoke(
            onSuccess = {
                assert(it.isEmpty())
            })
    }

    @Test
    fun `WHEN fetching the users BUT with error THEN should return a failure message`() {

        every { repository.fetchUsers() } returns null
        every { context.getString(any()) } returns "Error"

        fetchUsersUseCase.invoke(
            {}, onFailure = {
                assert(it == "Error")
            })
    }
}