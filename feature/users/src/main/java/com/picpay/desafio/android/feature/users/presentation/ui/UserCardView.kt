package com.picpay.desafio.android.feature.users.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.picpay.desafio.android.feature.users.R
import com.picpay.desafio.android.feature.users.databinding.UsersCardViewBinding
import com.picpay.desafio.android.feature.users.domain.model.UserVO
import com.picpay.desafio.core.util.loadImage

class UserCardView(
    context: Context,
    attributeSet: AttributeSet
) : LinearLayout(context, attributeSet) {

    companion object {
        private const val LOADING = 0
        private const val NORMAL = 1
    }

    var userVO: UserVO? = null
        set(value) {
            field = value
            setupView()
        }

    private val layoutInflater: LayoutInflater get() = LayoutInflater.from(context)

    private var binding = UsersCardViewBinding.inflate(
        layoutInflater,
        this,
        true
    )

    private fun setupView() {
        with(binding) {
            if (userVO?.isPlaceholder == true) {
                userCardFlipper.displayedChild = LOADING
            } else {
                userCardFlipper.displayedChild = NORMAL
                onBindView()
            }
        }
    }

    private fun onBindView() {
        with(binding.usersCardContent) {
            userVO?.user?.let { model ->
                name.text = model.name
                username.text = model.username
                picture.loadImage(
                    model.img,
                    R.drawable.ic_round_account_circle
                )
            }
        }
    }
}