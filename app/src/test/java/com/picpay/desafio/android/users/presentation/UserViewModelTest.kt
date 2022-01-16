package com.picpay.desafio.android.users.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.core.ui.ViewState
import com.picpay.desafio.android.users
import com.picpay.desafio.android.users.data.UsersRepository
import com.picpay.desafio.android.users.domain.FetchUsersUseCase
import com.picpay.desafio.android.users.presentation.ui.UserListAdapter
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

@ExperimentalCoroutinesApi
class UserViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testDipatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: UserViewModel

    private val adapter: UserListAdapter = mockk(relaxed = true)
    private val repository: UsersRepository = mockk(relaxed = true)
    private lateinit var fetchUsersUseCase: FetchUsersUseCase

    private val stateObs = spyk<Observer<ViewState>>(Observer { })

    @Before
    fun setup() {
        Dispatchers.setMain(testDipatcher)

        fetchUsersUseCase = FetchUsersUseCase(repository)

        viewModel = UserViewModel(
            fetchUsersUseCase,
            adapter
        ).apply {
            state.observeForever(stateObs)
        }

        every { repository.fetchUsers() } returns users
        every { adapter.users } returns users
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDipatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when fetching users should return it's list successfully`() = runBlocking {

        viewModel.fetch()

        verify { stateObs.onChanged(ViewState.NORMAL) }

        assert(adapter.users.isNotEmpty())
    }


    @Test
    fun `when fetching users should return a empty list successfully`() = runBlocking {

        every { repository.fetchUsers() } returns listOf()

        viewModel.fetch()

        verify { stateObs.onChanged(ViewState.NORMAL) }

        assert(adapter.users.isEmpty())
    }


    @Test
    fun `when fetching users should return an exception in case of error`() = runBlocking {

        every { repository.fetchUsers() } throws Exception("Error")

        viewModel.fetch()

        verify { stateObs.onChanged(ViewState.ERROR) }

        assert(adapter.users.isEmpty())
    }


}