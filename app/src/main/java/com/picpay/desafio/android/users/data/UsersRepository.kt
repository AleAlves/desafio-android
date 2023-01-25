package com.picpay.desafio.android.users.data

import com.picpay.desafio.android.users.data.model.User
import javax.inject.Inject


interface UsersRepository {
    fun fetchUsers(): List<User>?
    fun fetchUsersPlaceholders(): List<User>
}

class UsersRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UsersRepository {

    override fun fetchUsers(): List<User>? = userDataSource.fetch()
    override fun fetchUsersPlaceholders(): List<User> = userDataSource.fetchPlaceholders()
}