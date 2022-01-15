package com.picpay.desafio.android.users.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.picpay.desafio.android.users
import com.picpay.desafio.android.users.data.dao.UserDao
import okio.IOException
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserDataBaseTest {
    private lateinit var userDao: UserDao
    private lateinit var db: UserDataBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, UserDataBase::class.java
        ).build()
        userDao = db.user()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        userDao.insert(users)
        val query = userDao.fetch()
    }
}