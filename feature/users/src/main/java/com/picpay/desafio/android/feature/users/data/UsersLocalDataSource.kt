package com.picpay.desafio.android.feature.users.data

import com.google.gson.reflect.TypeToken
import com.picpay.desafio.android.feature.users.data.model.UserModel
import com.picpay.desafio.core.util.CacheProvider
import javax.inject.Inject

interface UserLocalDataSource : UserDataSource {

    fun stash(users: List<UserModel>?)
}

class UserLocalDataSourceImpl @Inject constructor(
    private val cache: CacheProvider,
    private val placeHolders: List<UserModel>
) : UserLocalDataSource {

    private val usersKey = "users"

    override fun fetchUsers(): List<UserModel> =
        cache.retrieve(usersKey, object : TypeToken<List<UserModel>>() {}.type)

    override fun fetchPlaceholders(): List<UserModel> = placeHolders

    override fun stash(users: List<UserModel>?) {
        cache.stash(usersKey, users)
    }
}