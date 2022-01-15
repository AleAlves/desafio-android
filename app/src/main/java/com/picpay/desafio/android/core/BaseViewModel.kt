package com.picpay.desafio.android.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.core.ui.ViewState

abstract class BaseViewModel : ViewModel() {

    internal val state: MutableLiveData<ViewState> = MutableLiveData()

    fun setViewState(state: ViewState) {
        this.state.postValue(state)
    }
}