package com.picpay.desafio.android.users.domain

import com.picpay.desafio.android.users.data.UsersRepository
import com.picpay.desafio.android.users.domain.model.User
import java.lang.Exception
import javax.inject.Inject

class FetchUsersUseCase @Inject constructor(private val repository: UsersRepository) {

    operator fun invoke(onSuccess: (List<User>) -> Unit, onError: (String?) -> Unit) {
        try {
            val data = repository.fetchUsers()
            data?.let { onSuccess.invoke(it) }
        } catch (e: Exception) {
            onError.invoke(e.message.toString())
        }
    }
}