package com.picpay.desafio.android.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.core.ui.ViewState

abstract class BaseViewModel : ViewModel() {

    internal val state: MutableLiveData<ViewState> = MutableLiveData()
    internal val error: MutableLiveData<String> = MutableLiveData()

    fun setViewState(state: ViewState) {
        this.state.postValue(state)
    }
}