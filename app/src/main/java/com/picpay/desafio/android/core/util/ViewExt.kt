package com.picpay.desafio.android.core.util

import android.view.View
import android.widget.ImageView
import com.picpay.desafio.android.R
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

fun ImageView.loadImage(name: String?) {
    Picasso.get()
        .load(name)
        .error(R.drawable.ic_round_account_circle)
        .placeholder(R.drawable.user_profile_pic_loading_animation)
        .into(this)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}