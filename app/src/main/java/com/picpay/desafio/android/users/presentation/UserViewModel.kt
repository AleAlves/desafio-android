package com.picpay.desafio.android.users.presentation

import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.core.BaseViewModel
import com.picpay.desafio.android.core.ui.ViewState
import com.picpay.desafio.android.users.domain.FetchUsersUseCase
import com.picpay.desafio.android.users.presentation.ui.UserListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val fetchUsersUseCase: FetchUsersUseCase,
    internal val adapter: UserListAdapter
) : BaseViewModel() {

    fun fetch() {
        viewModelScope.launch(Dispatchers.IO) {
            setViewState(ViewState.SKELETON)
            fetchUsersUseCase(
                onSuccess = {
                    adapter.users = it
                    setViewState(ViewState.NORMAL)
                }, onError = {
                    super.error.postValue(it)
                    setViewState(ViewState.ERROR)
                }
            )
        }
    }
}