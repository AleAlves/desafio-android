package com.picpay.desafio.android.core.util

import android.text.Editable
import android.text.TextWatcher
import java.text.SimpleDateFormat
import java.util.*


fun watcherToMmAaDate(): TextWatcher {
    var isDeleting = false
    return object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            isDeleting = after == 0
        }

        override fun afterTextChanged(s: Editable) {
            if (isDeleting.not() && s.length == 2) {
                s.append("/")
            }
        }
    }
}

fun String.getDateToString(): String {
    if (this.isEmpty())
        return this
    return SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ",
        Locale.getDefault()
    )
        .parse(this)
        .dateFormat() ?: ""
}

fun Date.dateFormat(): String? {
    return SimpleDateFormat("dd/MM/yyy HH:mm", Locale.getDefault()).format(this.time)
}