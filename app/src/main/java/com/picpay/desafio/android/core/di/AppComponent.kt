package com.picpay.desafio.android.core.di

import android.app.Application
import com.picpay.desafio.android.core.CustomMainApplication
import com.picpay.desafio.android.users.di.UsersModuleBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        RoomModule::class,
        UsersModuleBuilder::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    fun inject(application: CustomMainApplication)
    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}