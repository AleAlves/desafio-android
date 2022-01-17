package com.picpay.desafio.android.users.data

import com.picpay.desafio.android.users.domain.model.User
import java.lang.Exception
import javax.inject.Inject

interface UserDataSource {
    fun fetch(): List<User>?
}

interface UserRemoteDataSource : UserDataSource

interface UserLocalDataSource : UserDataSource {
    fun update(users: List<User>?)
}

class UserLocalDataSourceImpl @Inject constructor(
    private val userDataBase: UserDataBase
) : UserLocalDataSource {

    override fun fetch(): List<User>? {
        return userDataBase.user().fetch()
    }

    override fun update(users: List<User>?) {
        userDataBase.user().clear()
        userDataBase.user().update(users)
    }
}

class UserRemoteDataSourceImpl @Inject constructor(
    private val service: PicPayService
) : UserRemoteDataSource {

    override fun fetch(): List<User> {
        val data = service.getUsers().execute()
        return data.body() ?: listOf()
    }
}

class UserDataSourceImpl @Inject constructor(
    private val remote: UserRemoteDataSource,
    private val local: UserLocalDataSource
) :
    UserDataSource {
    override fun fetch(): List<User> {
        var data: List<User>?
        try {
            data = remote.fetch()
            if (data.isNullOrEmpty()) {
                data = local.fetch()
            } else {
                local.update(data)
            }
        } catch (e: Exception) {
            data = local.fetch()
        }
        return data ?: listOf()
    }
}