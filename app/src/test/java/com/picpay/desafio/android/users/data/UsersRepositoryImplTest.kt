package com.picpay.desafio.android.users.data


import com.picpay.desafio.android.users
import org.junit.Test
import org.junit.Before
import io.mockk.*

class UsersRepositoryImplTest {

    private lateinit var repository: UsersRepository

    private val userDataBase: UserDataSource = mockk()

    @Before
    fun setup() {
        repository = UsersRepositoryImpl(userDataBase)
    }

    @Test
    fun `given a requisition then should return a list of users successfully`() {
        every { userDataBase.fetch() } returns users

        repository.fetchUsers()
    }
}