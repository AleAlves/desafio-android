package com.picpay.desafio.android.users.presentation.ui

import com.picpay.desafio.android.core.ui.BaseViewHolder
import com.picpay.desafio.android.core.util.loadImage
import com.picpay.desafio.android.databinding.UsersViewItemBinding
import com.picpay.desafio.android.users.domain.model.User

class UserViewHolder(
    private val binding: UsersViewItemBinding
) : BaseViewHolder(binding.root) {

    override fun setupView(user: User?) {
        binding.name.text = user?.name
        binding.username.text = user?.username
        binding.picture.loadImage(user?.img)
    }
}