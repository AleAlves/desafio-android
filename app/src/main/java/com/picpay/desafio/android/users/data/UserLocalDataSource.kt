package com.picpay.desafio.android.users.data

import com.picpay.desafio.android.core.CacheProvider
import com.picpay.desafio.android.users.data.model.User
import javax.inject.Inject

interface UserLocalDataSource : UserDataSource {

    fun stash(users: List<User>?)
}

class UserLocalDataSourceImpl @Inject constructor(
    private val cache: CacheProvider,
    private val placeHolders: List<User>
) : UserLocalDataSource {

    private val usersKey = "users"

    override fun fetch(): List<User> = cache.retrieve(usersKey)

    override fun fetchPlaceholders(): List<User> = placeHolders

    override fun stash(users: List<User>?) {
        cache.stash(usersKey, users)
    }
}