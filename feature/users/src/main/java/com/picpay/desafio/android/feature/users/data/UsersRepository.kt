package com.picpay.desafio.android.feature.users.data

import com.picpay.desafio.android.feature.users.data.model.UserModel
import javax.inject.Inject

interface UsersRepository {
    fun fetchUsers(): List<UserModel>?
    fun fetchPlaceholders(): List<UserModel>
}

class UsersRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UsersRepository {

    override fun fetchUsers(): List<UserModel>? = userDataSource.fetchUsers()
    override fun fetchPlaceholders(): List<UserModel> = userDataSource.fetchPlaceholders()
}