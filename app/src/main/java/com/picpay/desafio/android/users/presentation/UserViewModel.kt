package com.picpay.desafio.android.users.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.core.BaseViewModel
import com.picpay.desafio.android.core.ui.ViewState
import com.picpay.desafio.android.users.domain.model.User
import com.picpay.desafio.android.users.domain.FetchUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val fetchUsersUseCase: FetchUsersUseCase
) : BaseViewModel() {

    val users: MutableLiveData<List<User>> = MutableLiveData()

    fun fetch() {
        viewModelScope.launch(Dispatchers.IO) {
            setViewState(ViewState.SKELETON)
            fetchUsersUseCase(
                onSuccess = {
                    setViewState(ViewState.NORMAL)
                    users.postValue(it)
                }, onError = {
                    setViewState(ViewState.ERROR)
                }
            )
        }
    }
}