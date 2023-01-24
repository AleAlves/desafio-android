package com.picpay.desafio.android.core.ui

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(){

    protected abstract fun onViewState()
}