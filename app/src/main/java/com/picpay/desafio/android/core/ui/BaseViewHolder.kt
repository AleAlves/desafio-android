package com.picpay.desafio.android.core.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.users.domain.model.User

abstract class BaseViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    protected abstract fun setupView(user: User?)

    var data: User? = null
        set(value) {
            setupView(value)
            field = value
        }
}
