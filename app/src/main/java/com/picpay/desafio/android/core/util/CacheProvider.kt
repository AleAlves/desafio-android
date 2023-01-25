package com.picpay.desafio.android.core

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.picpay.desafio.android.R
import com.picpay.desafio.android.users.data.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


interface CacheProvider {
    fun <T> stash(key: String, data: T)
    fun <T> retrieve(key: String): T
}

class CacheProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CacheProvider {

    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.masterkey),
        Context.MODE_PRIVATE
    )

    override fun <T> stash(key: String, data: T) {
        sharedPreferences.edit().apply {
            putString(key, Gson().toJson(data))
        }.apply()
    }

    override fun <T> retrieve(key: String): T {
        val gson = GsonBuilder().create()
        val type = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(sharedPreferences.getString(key, ""), type) as T
    }
}
