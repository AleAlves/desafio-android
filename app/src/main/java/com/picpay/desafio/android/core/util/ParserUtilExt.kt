package com.picpay.desafio.android.core.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


inline fun <reified T> String?.parseFromJson() : T? {
    return Gson().fromJson(this, object : TypeToken<T>() {}.type)
}
