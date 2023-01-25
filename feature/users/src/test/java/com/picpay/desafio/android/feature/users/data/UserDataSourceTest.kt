package com.picpay.desafio.android.feature.users.data

import com.picpay.desafio.android.feature.users.data.model.UserModel
import com.picpay.desafio.android.feature.users.users
import com.picpay.desafio.core.util.Tracker
import io.mockk.*
import org.junit.Before
import org.junit.Test

internal class UserDataSourceTest {

    private lateinit var dataSource: UserDataSource

    private val local: UserLocalDataSource = mockk()
    private val remote: UserRemoteDataSource = mockk()
    private val tracker: Tracker = mockk()

    @Before
    fun setup() {
        dataSource = UserDataSourceImpl(remote, local, tracker)
    }

    @Test
    fun `WHEN fetching the users from data source THEN must returns it successfully`() {

        every { remote.fetchUsers() } returns users
        every { local.stash(users) } just Runs
        every { local.fetchUsers() } returns users

        val response = dataSource.fetchUsers()

        verify { local.fetchUsers() }
        verify { remote.fetchUsers() }
        assert(response == users)
    }

    @Test
    fun `WHEN fetching the placeholders from data source THEN must returns it successfully`() {

        every { local.fetchPlaceholders() } returns users

        val response = dataSource.fetchPlaceholders()

        verify { local.fetchPlaceholders() }
        assert(response == users)
    }

    @Test
    fun `WHEN fetching the users BUT with error THEN must returns it successfully`() {

        val error = Exception()
        every { tracker.log(any()) } just Runs
        every { local.fetchUsers() } returns listOf()
        every { remote.fetchUsers() } throws error

        dataSource.fetchUsers()

        verify { tracker.log(error) }
    }
}