package com.picpay.desafio.core.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.reflect.Type
import javax.inject.Inject


interface CacheProvider {
    fun <T> stash(key: String, data: T)
    fun <T> retrieve(key: String, type: Type): T
}

class CacheProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CacheProvider {

    private var masterkey = "picpaylocalstash"

    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(
        masterkey,
        Context.MODE_PRIVATE
    )

    override fun <T> stash(key: String, data: T) {
        sharedPreferences.edit().apply {
            putString(key, Gson().toJson(data))
        }.apply()
    }

    override fun <T> retrieve(key: String, type: Type): T {
        return Gson().fromJson(sharedPreferences.getString(key, ""), type)
    }
}
