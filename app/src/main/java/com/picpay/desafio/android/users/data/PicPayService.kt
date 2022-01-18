package com.picpay.desafio.android.users.data

import com.picpay.desafio.android.users.domain.model.User
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<User>>
}