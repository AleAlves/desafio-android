package com.picpay.desafio.android.users.data.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.picpay.desafio.android.users.domain.model.User

class UserTypeConvert {
    @TypeConverter
    fun fromString(user: String?): User? {
        if (user == null) return null
        return Gson().fromJson<User>(
            user,
            User::class.java
        )
    }

    @TypeConverter
    fun toString(user: User?): String? {
        if (user == null) return null
        return Gson().toJson(user)
    }
}