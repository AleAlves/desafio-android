package com.picpay.desafio.android.feature.users.presentation

import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.feature.users.domain.FetchUsersUseCase
import com.picpay.desafio.android.feature.users.domain.FetchPlaceholderUseCase
import com.picpay.desafio.android.feature.users.domain.model.UserVO
import com.picpay.desafio.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val fetchUsersUseCase: FetchUsersUseCase,
    private val placeHolderUsersUseCase: FetchPlaceholderUseCase
) : BaseViewModel<UserViewModel.UsersViewState>() {

    init {
        fetch()
    }

    fun fetch() {
        viewModelScope.launch(Dispatchers.IO) {
            loadPlaceholders()
            loadUsers()
        }
    }

    private fun loadPlaceholders() {
        placeHolderUsersUseCase(
            onSuccess = {
                onLoadPlaceholders(it)
            }
        )
    }

    private fun loadUsers() {
        fetchUsersUseCase(
            onSuccess = {
                onLoadUsers(it)
            },
            onFailure = {
                onFailure(it)
            }
        )
    }

    private fun onLoadUsers(vos: List<UserVO>) {
        state.postValue(UsersViewState.OnLoadUsers(vos))
    }

    private fun onLoadPlaceholders(vos: List<UserVO>) {
        state.postValue(UsersViewState.OnLoadPlaceholders(vos))
    }

    private fun onFailure(message: String) {
        state.postValue(UsersViewState.OnFailure(message))
    }

    sealed class UsersViewState : com.picpay.desafio.core.ui.ViewState() {
        data class OnFailure(val message: String) : UsersViewState()
        data class OnLoadUsers(val vos: List<UserVO>) : UsersViewState()
        data class OnLoadPlaceholders(val vos: List<UserVO>) : UsersViewState()
    }
}