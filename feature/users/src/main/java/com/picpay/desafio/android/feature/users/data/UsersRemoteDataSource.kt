package com.picpay.desafio.android.feature.users.data

import com.picpay.desafio.android.feature.users.data.model.UserModel
import javax.inject.Inject

interface UserRemoteDataSource : UserDataSource

class UserRemoteDataSourceImpl @Inject constructor(
    private val service: PicPayService
) : UserRemoteDataSource {

    override fun fetchUsers(): List<UserModel>? = service.getUsers().execute().body()
}