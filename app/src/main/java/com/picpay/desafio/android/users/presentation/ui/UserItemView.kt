package com.picpay.desafio.android.users.presentation.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.users.domain.model.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserItemView(context: Context) : LinearLayout(context) {

    private val textViewName: TextView
    private val textViewUserName: TextView
    private val imageViewUserPic: ImageView
    private val progressImagePlaceholder: ProgressBar
    private val layoutInflater: LayoutInflater get() = LayoutInflater.from(context)

    var user: User? = null
        set(value) {
            field = value
            bind(value)
        }

    init {
        layoutInflater.inflate(R.layout.list_item_user, this, true)
        textViewName = findViewById(R.id.name)
        textViewUserName = findViewById(R.id.username)
        imageViewUserPic = findViewById(R.id.picture)
        progressImagePlaceholder = findViewById(R.id.progressBar)
    }

    private fun bind(user: User?) {
        textViewName.text = user?.name
        textViewUserName.text = user?.username
        progressImagePlaceholder.visibility = View.VISIBLE
        Picasso.get()
            .load(user?.img)
            .error(R.drawable.ic_round_account_circle)
            .into(imageViewUserPic, object : Callback {
                override fun onSuccess() {
                    imageViewUserPic.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    imageViewUserPic.visibility = View.GONE
                }
            })
    }
}