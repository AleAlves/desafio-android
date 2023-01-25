package com.picpay.desafio.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.core.ui.BaseViewState

abstract class BaseViewModel<T : BaseViewState> : ViewModel() {

    val state: MutableLiveData<T> = MutableLiveData()

    fun setState(newState: BaseViewState) {
        state.postValue(newState as T)
    }
}