package com.picpay.desafio.android.core

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.picpay.desafio.android.users.domain.model.User


abstract class BaseUseCase<T> {

    abstract fun invoke(
        onSuccess: (T) -> Unit,
        onError: (String?) -> Unit
    )
}
