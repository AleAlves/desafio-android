package com.picpay.desafio.android.users.presentation.ui

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.core.util.loadImage
import com.picpay.desafio.android.databinding.UserListItemBinding
import com.picpay.desafio.android.databinding.UsersViewBinding
import com.picpay.desafio.android.users.domain.model.User

class UserItemView(context: Context) : LinearLayout(context) {

    private val layoutInflater: LayoutInflater get() = LayoutInflater.from(context)

    private var binding: UserListItemBinding = UserListItemBinding.inflate(
        layoutInflater,
        this,
        true
    )

    var user: User? = null
        set(value) {
            field = value
            bind(value)
        }

    private fun bind(user: User?) {
        binding.name.text = user?.name
        binding.username.text = user?.username
        binding.picture.loadImage(user?.img, binding.progressBar)
    }
}