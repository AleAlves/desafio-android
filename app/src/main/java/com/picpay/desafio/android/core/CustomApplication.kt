package com.picpay.desafio.android.core

import com.picpay.desafio.android.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class CustomMainApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}