package com.picpay.desafio.android.users.data

import com.picpay.desafio.android.users.data.model.User
import javax.inject.Inject

interface UserRemoteDataSource : UserDataSource

class UserRemoteDataSourceImpl @Inject constructor(
    private val service: PicPayService
) : UserRemoteDataSource {

    override fun fetch(): List<User> = service.getUsers().execute().body() ?: listOf()
}