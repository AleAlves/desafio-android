package com.picpay.desafio.android.feature.users.di

import com.picpay.desafio.android.feature.users.data.*
import com.picpay.desafio.android.feature.users.data.model.UserModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class UsersModule {

    @Binds
    abstract fun bindsUsersRepository(repository: UsersRepositoryImpl): UsersRepository

    @Binds
    abstract fun bindsUserRemoteDataSource(remote: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    abstract fun bindsUserLocalDataSource(local: UserLocalDataSourceImpl): UserLocalDataSource

    @Binds
    abstract fun bindsUserDataSource(dataSource: UserDataSourceImpl): UserDataSource

    @Binds
    abstract fun bindsCacheProvider(provider: com.picpay.desafio.core.util.CacheProviderImpl): com.picpay.desafio.core.util.CacheProvider

    @Binds
    abstract fun bindsTracker(provider: com.picpay.desafio.core.util.TrackerImpl): com.picpay.desafio.core.util.Tracker

    companion object {

        @Provides
        fun providesPicPayService(retrofit: Retrofit): PicPayService {
            return retrofit.create(PicPayService::class.java)
        }

        @Provides
        fun providesPlaceholders(): List<UserModel> =
            List(20) {
                UserModel(null, null, null, null)
            }.toList()
    }
}