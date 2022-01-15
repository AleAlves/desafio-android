package com.picpay.desafio.android.users.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class User(
    @PrimaryKey
    @SerializedName("id") val id: Int?,
    @SerializedName("img") val img: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("username") val username: String?
)