package com.picpay.desafio.android.users.domain

import com.picpay.desafio.android.core.BaseUseCase
import com.picpay.desafio.android.users.data.UsersRepository
import com.picpay.desafio.android.users.domain.model.User
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject

class FetchUsersUseCase @Inject constructor(
    private val repository: UsersRepository
) : BaseUseCase<List<User>>() {

    override operator fun invoke(
        onSuccess: (List<User>) -> Unit, onFailure: (String?) -> Unit
    ) {
        try {
            repository.fetchUsers()?.let { onSuccess.invoke(it) }
        } catch (e: Exception) {
            onFailure.invoke(
                when (e) {
                    is UnknownHostException -> "Server on maintainance"
                    else -> "An error ocourred, please try again later"
                }
            )
        }
    }
}