package com.picpay.desafio.core.util

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(name: String?, placeholder: Int) {
    Picasso.get()
        .load(name)
        .error(placeholder)
        .placeholder(placeholder)
        .into(this)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}