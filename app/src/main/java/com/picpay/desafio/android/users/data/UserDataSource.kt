package com.picpay.desafio.android.users.data

import com.picpay.desafio.android.users.domain.model.User
import javax.inject.Inject

interface UserDataSource {
    fun fetch(): List<User>?
}

interface UserRemoteDataSource : UserDataSource

interface UserLocalDataSource : UserDataSource {
    fun setCache(users: List<User>?)
}

class UserLocalDataSourceImpl @Inject constructor() : UserLocalDataSource {

    override fun fetch(): List<User> {
        return listOf()
    }

    override fun setCache(users: List<User>?) {
    }
}

class UserRemoteDataSourceImpl @Inject constructor(
    private val service: PicPayService
) : UserRemoteDataSource {

    override fun fetch(): List<User>? = service.getUsers().execute().body()
}

class UserDataSourceImpl @Inject constructor(
    private val remote: UserRemoteDataSource,
    private val local: UserLocalDataSource
) :
    UserDataSource {
    override fun fetch() = remote.fetch()
}