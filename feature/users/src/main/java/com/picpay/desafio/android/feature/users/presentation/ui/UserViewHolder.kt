package com.picpay.desafio.android.feature.users.presentation.ui


import com.picpay.desafio.android.feature.users.R
import com.picpay.desafio.android.feature.users.databinding.UsersCollectionViewItemBinding
import com.picpay.desafio.android.feature.users.databinding.UsersCollectionViewPlaceholderBinding
import com.picpay.desafio.android.feature.users.domain.model.UserVO
import com.picpay.desafio.core.ui.BaseViewHolder
import com.picpay.desafio.core.util.loadImage

class UserViewHolder(
    private val binding: UsersCollectionViewItemBinding
) : BaseViewHolder<UserVO>(binding.root) {

    override fun setupView(vo: UserVO?) {
        vo?.user.let {
            binding.name.text = it?.name
            binding.username.text = it?.username
            binding.picture.loadImage(it?.img, R.drawable.ic_round_account_circle)
        }
    }
}

class UserPlaceholderViewHolder(
    binding: UsersCollectionViewPlaceholderBinding
) : BaseViewHolder<UserVO>(binding.root) {

    override fun setupView(vo: UserVO?) {}
}