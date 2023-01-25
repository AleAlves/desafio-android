package com.picpay.desafio.android.users.di

import androidx.constraintlayout.widget.Placeholder
import com.picpay.desafio.android.core.CacheProvider
import com.picpay.desafio.android.core.CacheProviderImpl
import com.picpay.desafio.android.core.util.Tracker
import com.picpay.desafio.android.core.util.TrackerImpl
import com.picpay.desafio.android.users.data.*
import com.picpay.desafio.android.users.data.model.User
import com.picpay.desafio.android.users.presentation.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserModule {

    @Binds
    abstract fun bindsUsersRepository(repository: UsersRepositoryImpl): UsersRepository

    @Binds
    abstract fun bindsUserRemoteDataSource(remote: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    abstract fun bindsUserLocalDataSource(local: UserLocalDataSourceImpl): UserLocalDataSource

    @Binds
    abstract fun bindsUserDataSource(dataSource: UserDataSourceImpl): UserDataSource

    @Binds
    abstract fun bindsCacheProvider(provider: CacheProviderImpl): CacheProvider

    @Binds
    abstract fun bindsTracker(provider: TrackerImpl): Tracker

    companion object {

        @Provides
        fun providesPicPayService(retrofit: Retrofit): PicPayService {
            return retrofit.create(PicPayService::class.java)
        }

        @Provides
        fun providesPlaceholders(): List<User> =
            List(20) {
                User(
                    null, null, null, null
                )
            }.toList()
    }
}