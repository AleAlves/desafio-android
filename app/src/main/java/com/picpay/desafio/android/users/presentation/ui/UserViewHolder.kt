package com.picpay.desafio.android.users.presentation.ui

import com.picpay.desafio.android.core.ui.BaseViewHolder
import com.picpay.desafio.android.core.util.loadImage
import com.picpay.desafio.android.databinding.UsersCollectionViewItemBinding
import com.picpay.desafio.android.databinding.UsersCollectionViewPlaceholderBinding
import com.picpay.desafio.android.users.domain.UserVO

class UserViewHolder(
    private val binding: UsersCollectionViewItemBinding
) : BaseViewHolder(binding.root) {

    override fun setupView(vo: UserVO?) {
        if (vo?.isPlaceholder == true) {

        } else {
            vo?.user.let {
                binding.name.text = it?.name
                binding.username.text = it?.username
                binding.picture.loadImage(it?.img)
            }
        }
    }
}

class UserPlaceholderViewHolder(
    private val binding: UsersCollectionViewPlaceholderBinding
) : BaseViewHolder(binding.root) {

    override fun setupView(vo: UserVO?) {}
}