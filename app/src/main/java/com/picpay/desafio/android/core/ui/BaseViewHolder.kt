package com.picpay.desafio.android.core.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.users.data.model.User
import com.picpay.desafio.android.users.domain.UserVO

abstract class BaseViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    protected abstract fun setupView(vo: UserVO?)

    var data: UserVO? = null
        set(value) {
            setupView(value)
            field = value
        }
}
