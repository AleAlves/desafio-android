package com.picpay.desafio.android.users.di

import com.picpay.desafio.android.users.data.*
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
    abstract fun bindsUserRemoteDataSource(repository: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    abstract fun bindsUserLocalDataSource(repository: UserLocalDataSourceImpl): UserLocalDataSource

    @Binds
    abstract fun bindsUserDataSource(repository: UserDataSourceImpl): UserDataSource

    companion object {

        @Provides
        fun providePicPayService(retrofit: Retrofit): PicPayService {
            return retrofit.create(PicPayService::class.java)
        }
    }
}