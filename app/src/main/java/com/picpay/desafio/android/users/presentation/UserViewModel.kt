package com.picpay.desafio.android.users.presentation

import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.core.BaseViewModel
import com.picpay.desafio.android.core.ui.ViewState
import com.picpay.desafio.android.users.domain.FetchUsersUseCase
import com.picpay.desafio.android.users.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val fetchUsersUseCase: FetchUsersUseCase
) : BaseViewModel<UserViewModel.UsersViewState>() {

    fun fetch() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchUsersUseCase(
                {
                    onLoadUsers(it)
                }, {
                    onFailure(it.toString())
                }
            )
        }
    }

    private fun onLoadUsers(users: List<User>) {
        state.postValue(UsersViewState.OnLoadForms(users))
    }

    private fun onFailure(message: String) {
        state.postValue(UsersViewState.OnFailure(message))
    }

    sealed class UsersViewState : ViewState() {
        data class OnLoadForms(val users: List<User>) : UsersViewState()
        data class OnFailure(val message: String) : UsersViewState()
    }
}