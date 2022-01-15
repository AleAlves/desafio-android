package com.picpay.desafio.android.users.di

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.core.di.AcitivtyScope
import com.picpay.desafio.android.core.di.ViewModelKey
import com.picpay.desafio.android.users.data.*
import com.picpay.desafio.android.users.presentation.UserViewModel
import com.picpay.desafio.android.users.presentation.ui.UsersActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(includes = [UserModule::class])
abstract class UsersModuleBuilder

@Module
abstract class UserModule {

    @ContributesAndroidInjector
    @AcitivtyScope
    abstract fun bindModule(): UsersActivity

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindViewModel(viewModel: UserViewModel): ViewModel

    @Binds
    abstract fun bindRepository(repository: UsersRepositoryImpl): UsersRepository

    @Binds
    abstract fun bindUserDataSource(dataSource: UserDataSourceImpl): UserDataSource

    @Binds
    abstract fun bindUserRemoteDataSource(remote: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    abstract fun bindUserLocalDataSource(local: UserLocalDataSourceImpl): UserLocalDataSource

    companion object {

        @Provides
        @JvmStatic
        fun providePicPayService(retrofit: Retrofit): PicPayService {
            return retrofit.create(PicPayService::class.java)
        }
    }
}
