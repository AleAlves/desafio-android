package com.picpay.desafio.android.core.di

import android.app.Application
import androidx.room.Room
import com.picpay.desafio.android.users.data.UserDataBase
import com.picpay.desafio.android.users.data.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(application: Application): UserDataBase {
        return Room.databaseBuilder(application, UserDataBase::class.java, "users").build()
    }

    @Singleton
    @Provides
    fun providesCartDao(demoDatabase: UserDataBase): UserDao {
        return demoDatabase.user()
    }
}