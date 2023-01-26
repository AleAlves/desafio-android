package com.picpay.desafio.android.feature.users.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.feature.users.databinding.UsersCollectionViewBinding
import com.picpay.desafio.android.feature.users.domain.model.UserVO
import com.picpay.desafio.android.feature.users.presentation.ui.adapter.UsersAdapter

class UsersListView(
    context: Context,
    attributeSet: AttributeSet
) : LinearLayout(context, attributeSet) {

    private val adapter = UsersAdapter()

    private var onRefresh: () -> Unit = {}

    private var binding = UsersCollectionViewBinding.inflate(
        LayoutInflater.from(context),
        this
    )

    init {
        with(binding) {
            usersCollectionRecyclerview.layoutManager = LinearLayoutManager(context).apply {
                orientation = RecyclerView.VERTICAL
            }
            usersCollectionRecyclerview.adapter = adapter
            with(binding.usersSwipeLayout) {
                setOnRefreshListener {
                    onRefresh.invoke()
                    isRefreshing = false
                }
            }
        }
    }

    fun setData(vos: List<UserVO>) {
        adapter.items.submitList(vos)
    }

    fun onRefreshListener(listener: () -> Unit) {
        onRefresh = listener
    }
}