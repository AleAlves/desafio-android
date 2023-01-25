package com.picpay.desafio.android.feature.users.domain.model

import com.picpay.desafio.android.feature.users.data.model.UserModel

data class UserVO(val user: UserModel, val isPlaceholder: Boolean = false)