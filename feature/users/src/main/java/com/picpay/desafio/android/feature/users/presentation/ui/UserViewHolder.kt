package com.picpay.desafio.android.feature.users.presentation.ui


import com.picpay.desafio.android.feature.users.databinding.UsersCardViewHolderBinding
import com.picpay.desafio.android.feature.users.domain.model.UserVO
import com.picpay.desafio.core.ui.BaseViewHolder

class UserViewHolder(
    private val binding: UsersCardViewHolderBinding
) : BaseViewHolder<UserVO>(binding.root) {

    override fun setupView(vo: UserVO?) {
        binding.userCardView.userVO = vo
    }
}