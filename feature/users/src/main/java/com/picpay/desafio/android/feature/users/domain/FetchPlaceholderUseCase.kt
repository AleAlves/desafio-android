package com.picpay.desafio.android.feature.users.domain

import com.picpay.desafio.android.feature.users.data.UsersRepository
import com.picpay.desafio.android.feature.users.domain.model.UserVO
import com.picpay.desafio.core.BaseUseCase
import javax.inject.Inject

class FetchPlaceholderUseCase @Inject constructor(
    private val repository: UsersRepository
) : BaseUseCase<List<UserVO>>() {

    override operator fun invoke(
        onSuccess: (List<UserVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        repository.fetchPlaceholders().run {
            onSuccess.invoke(this.map {
                UserVO(user = it, isPlaceholder = true)
            })
        }
    }
}