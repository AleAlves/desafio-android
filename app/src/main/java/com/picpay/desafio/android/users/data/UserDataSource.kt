package com.picpay.desafio.android.users.data

import com.picpay.desafio.android.core.util.Tracker
import com.picpay.desafio.android.users.data.model.User
import javax.inject.Inject

interface UserDataSource {

    fun fetch(): List<User>?
    fun fetchPlaceholders(): List<User> = listOf()
}

class UserDataSourceImpl @Inject constructor(
    private val remote: UserRemoteDataSource,
    private val local: UserLocalDataSource,
    private val tracker: Tracker
) : UserDataSource {

    override fun fetch(): List<User> {
        return with(local) {
            try {
                stashFreshData()
            } catch (e: Exception) {
                tracker.log(e)
            }
            fetch() ?: listOf()
        }
    }

    private fun stashFreshData() {
        local.stash(remote.fetch())
    }

    override fun fetchPlaceholders(): List<User> = local.fetchPlaceholders()
}