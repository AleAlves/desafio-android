package com.picpay.desafio.android.feature.users.data

import com.picpay.desafio.android.feature.users.data.model.UserModel
import com.picpay.desafio.core.util.Tracker
import javax.inject.Inject

interface UserDataSource {

    fun fetchUsers(): List<UserModel>?
    fun fetchPlaceholders(): List<UserModel> = listOf()
}

class UserDataSourceImpl @Inject constructor(
    private val remote: UserRemoteDataSource,
    private val local: UserLocalDataSource,
    private val tracker: Tracker
) : UserDataSource {

    override fun fetchUsers(): List<UserModel> {
        return with(local) {
            try {
                stashNewData()
            } catch (e: Exception) {
                tracker.log(e)
            }
            fetchUsers() ?: listOf()
        }
    }

    private fun stashNewData() {
        local.stash(remote.fetchUsers())
    }

    override fun fetchPlaceholders(): List<UserModel> = local.fetchPlaceholders()
}