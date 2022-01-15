package com.picpay.desafio.android.users.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.picpay.desafio.android.users.domain.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun fetch(): List<User>?

    @Insert
    fun update(item: List<User>?)

    @Query("DELETE from user")
    fun clear()
}