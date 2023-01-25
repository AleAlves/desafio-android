package com.picpay.desafio.core.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(
    view: View
) : RecyclerView.ViewHolder(view) {

    protected abstract fun setupView(vo: T?)

    var data: T? = null
        set(value) {
            setupView(value)
            field = value
        }
}
