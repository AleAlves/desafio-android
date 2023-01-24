package com.picpay.desafio.android.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.core.ui.ViewState

abstract class BaseViewModel<T : ViewState> : ViewModel() {

    val state: MutableLiveData<T> = MutableLiveData()
}