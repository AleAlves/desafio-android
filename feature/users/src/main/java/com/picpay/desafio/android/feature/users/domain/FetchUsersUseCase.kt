package com.picpay.desafio.android.feature.users.domain

import android.content.Context
import com.picpay.desafio.android.feature.users.R
import com.picpay.desafio.android.feature.users.data.UsersRepository
import com.picpay.desafio.android.feature.users.domain.model.UserVO
import com.picpay.desafio.core.BaseUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FetchUsersUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: UsersRepository
) : BaseUseCase<List<UserVO>>() {

    override operator fun invoke(
        onSuccess: (List<UserVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        repository.fetchUsers().run {
            if (this == null) {
                onFailure.invoke(context.getString(R.string.error))
            } else {
                onSuccess.invoke(this.map {
                    UserVO(user = it)
                })
            }
        }
    }
}