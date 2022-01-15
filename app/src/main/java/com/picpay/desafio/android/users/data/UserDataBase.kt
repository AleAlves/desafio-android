package com.picpay.desafio.android.users.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.picpay.desafio.android.users.data.model.User
import com.picpay.desafio.android.users.data.dao.UserDao
import com.picpay.desafio.android.users.data.dao.UserTypeConvert

@Database(entities = [User::class], version = 1)
@TypeConverters(UserTypeConvert::class)
abstract class UserDataBase : RoomDatabase() {
    abstract fun user(): UserDao
}