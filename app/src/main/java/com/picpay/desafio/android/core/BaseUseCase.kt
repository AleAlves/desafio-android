package com.picpay.desafio.android.core

abstract class BaseUseCase<T> {

    abstract fun invoke(
        onSuccess: (T) -> Unit,
        onError: (String?) -> Unit
    )
}
