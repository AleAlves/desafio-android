package com.picpay.desafio.android.users.data

import com.picpay.desafio.android.users.data.model.User
import javax.inject.Inject

interface UserDataSource {
    fun fetch(): List<User>?
}

class UserLocalDataSource @Inject constructor(
    private val userDataBase: UserDataBase
) : UserDataSource {

    override fun fetch(): List<User>? {
        return userDataBase.user().fetch()
    }
}

class UserRemoteDataSource @Inject constructor(
    private val service: PicPayService
) : UserDataSource {

    override fun fetch(): List<User>? {
        return service.getUsers().execute().body()
    }
}

class UserDataSourceImpl @Inject constructor(
    private val remote: UserRemoteDataSource,
    private val local: UserLocalDataSource
) :
    UserDataSource {
    override fun fetch(): List<User> {
        var data = local.fetch()
        if (data.isNullOrEmpty()) {
            data = remote.fetch()
        }
        return data ?: listOf()
    }
}