package com.picpay.desafio.android.users.data

import com.picpay.desafio.android.users.data.model.User
import javax.inject.Inject


interface UsersRepository {
    fun fetchUsers() : List<User>?
}

class UsersRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UsersRepository {

    override fun fetchUsers(): List<User>? {
        return userDataSource.fetch()
    }
}