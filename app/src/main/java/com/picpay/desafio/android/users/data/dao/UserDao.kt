package com.picpay.desafio.android.users.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.picpay.desafio.android.users.data.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun fetch(): List<User>?

    @Insert
    fun insert(item: List<User>)

    @Query("DELETE from user")
    fun clear()
}