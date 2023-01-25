package com.picpay.desafio.android.users.domain

import com.picpay.desafio.android.users.data.model.User

data class UserVO(val user: User, val isPlaceholder: Boolean = false)