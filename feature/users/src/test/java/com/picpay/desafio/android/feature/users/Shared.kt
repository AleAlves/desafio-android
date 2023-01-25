package com.picpay.desafio.android.feature.users

import com.picpay.desafio.android.feature.users.data.model.UserModel
import com.picpay.desafio.android.feature.users.domain.model.UserVO

val user = UserModel(null, null, null, null)

val users = List(10) {
    user
}.toList()