package com.picpay.desafio.core

abstract class BaseUseCase<T> {

    abstract operator fun invoke(
        onSuccess: (T) -> Unit,
        onFailure: (String) -> Unit = {}
    )
}
