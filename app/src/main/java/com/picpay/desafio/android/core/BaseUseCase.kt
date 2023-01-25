package com.picpay.desafio.android.core

abstract class BaseUseCase<T> {

    abstract operator fun invoke(
        onSuccess: (T) -> Unit,
        onFailure: (String) -> Unit
    )
}
