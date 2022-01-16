package com.picpay.desafio.android.core.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.picpay.desafio.android.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


fun ImageView.loadImage(name: String?, progressBar: ProgressBar) {
    progressBar.visibility = View.VISIBLE
    Picasso.get()
        .load(name)
        .error(R.drawable.ic_round_account_circle)
        .into(this, object : Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                progressBar.visibility = View.GONE
            }
        })
}