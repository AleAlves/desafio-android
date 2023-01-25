package com.picpay.desafio.android.feature.users.domain

import android.content.Context
import com.picpay.desafio.android.feature.users.data.UsersRepository
import com.picpay.desafio.android.feature.users.users
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test


internal class FetchPlaceholderUseCaseTest {

    private lateinit var placeholdersUseCase: FetchPlaceholderUseCase
    private val repository: UsersRepository = mockk(relaxed = true)
    private val context: Context = mockk(relaxed = true)

    @Before
    fun setup() {
        placeholdersUseCase = FetchPlaceholderUseCase(repository)
    }

    @Test
    fun `WHEN fetching the placeholders THEN must return it successfully`() {

        every { repository.fetchPlaceholders() } returns users

        placeholdersUseCase.invoke(
            onSuccess = {
                assert(it.isNotEmpty())
            })
    }

    @Test
    fun `WHEN fetching the placeholders BUT without registers THEN must return an empty list`() {

        every { repository.fetchPlaceholders() } returns listOf()

        placeholdersUseCase.invoke(
            onSuccess = {
                assert(it.isEmpty())
            })
    }
}