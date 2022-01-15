package com.picpay.desafio.android.users.di

import com.picpay.desafio.android.users.data.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [UserModule::class])
abstract class UsersModuleBuilder

@Module
abstract class UserModule {

    @Binds
    abstract fun bindRepository(repository: UsersRepositoryImpl): UsersRepository

    @Binds
    abstract fun bindUserDataSource(dataSource: UserDataSourceImpl): UserDataSource

    @Binds
    abstract fun bindUserRemoteDataSource(remote: UserRemoteDataSource): UserDataSource

    @Binds
    abstract fun bindUserLocalDataSource(local: UserLocalDataSource): UserDataSource

    companion object {

        @Provides
        @JvmStatic
        fun providePicPayService(retrofit: Retrofit): PicPayService {
            return retrofit.create(PicPayService::class.java)
        }
    }
}
