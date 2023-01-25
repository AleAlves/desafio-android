package com.picpay.desafio.android.feature.users.presentation

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.feature.users.data.UsersRepository
import com.picpay.desafio.android.feature.users.domain.FetchPlaceholderUseCase
import com.picpay.desafio.android.feature.users.domain.FetchUsersUseCase
import com.picpay.desafio.android.feature.users.domain.model.UserVO
import com.picpay.desafio.android.feature.users.users
import com.picpay.desafio.core.ui.BaseViewState
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class UserViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: UserViewModel

    private val context: Context = mockk()
    private val repository: UsersRepository = mockk(relaxed = true)
    private val stateObs: Observer<BaseViewState> = mockk(relaxed = true)

    private val usersUseCase: FetchUsersUseCase = FetchUsersUseCase(context, repository)
    private val placeholdersUseCase: FetchPlaceholderUseCase = FetchPlaceholderUseCase(repository)

    private var error = ""
    private var vos = listOf<UserVO>()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UserViewModel(usersUseCase, placeholdersUseCase).apply {
            state.observeForever(stateObs)
        }
        usersUseCase.invoke({ vos = it }, { error = it })
        placeholdersUseCase.invoke({ vos = it }, { error = it })
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN fetching users THEN must return a list of placeholders and users view objects`() {
        every { repository.fetchPlaceholders() } returns users
        every { repository.fetchUsers() } returns users

        viewModel.fetch()

        verifyOrder {
            stateObs.onChanged(UserViewModel.UsersViewState.OnLoadPlaceholders(vos))
            stateObs.onChanged(UserViewModel.UsersViewState.OnLoadUsers(vos))
        }
    }

    @Test
    fun `WHEN fetching users THEN must return a list of placeholders and and empty list`() {
        every { repository.fetchPlaceholders() } returns users
        every { repository.fetchUsers() } returns listOf()

        viewModel.fetch()

        verifyOrder {
            stateObs.onChanged(UserViewModel.UsersViewState.OnLoadPlaceholders(vos))
            stateObs.onChanged(UserViewModel.UsersViewState.OnLoadUsers(listOf()))
        }
    }


    @Test
    fun `WHEN fetching users BUT with error with THEN must return a message`() {
        every { context.getString(any()) } returns "error"
        every { repository.fetchPlaceholders() } returns users
        every { repository.fetchUsers() } returns null

        viewModel.fetch()

        verifyOrder {
            stateObs.onChanged(UserViewModel.UsersViewState.OnLoadPlaceholders(vos))
            stateObs.onChanged(UserViewModel.UsersViewState.OnFailure("error"))
        }
    }
}