package com.picpay.desafio.android.core.util

import android.content.Context


fun getDrawableResWithName(context: Context?, name: String): Int {
    val resources = context?.resources
    return resources?.getIdentifier(name, "drawable", context.packageName) ?: 0
}

fun Context.getPlaceHoldedString(res: Int, value: String): String {
    return String.format(getString(res, value))
}