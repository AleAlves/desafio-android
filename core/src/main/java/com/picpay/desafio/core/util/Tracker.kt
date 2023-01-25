package com.picpay.desafio.core.util

import android.util.Log
import javax.inject.Inject

interface Tracker {

    fun log(exception: Exception)
}

class TrackerImpl @Inject constructor() : Tracker {

    override fun log(exception: Exception) {
        exception.message?.let { Log.e("PICPAY", it) }
    }
}