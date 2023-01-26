package com.picpay.desafio.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.core.ui.BaseViewState

abstract class BaseViewModel<T : BaseViewState> : ViewModel() {

    private val _state: MutableLiveData<T> = MutableLiveData()
    val state: LiveData<T> = _state

    fun setViewState(newState: BaseViewState) {
        _state.postValue(newState as T)
    }
}