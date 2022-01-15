package com.picpay.desafio.android.users.presentation.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.users.domain.model.User

class UsersCollectionViewHolder(val view: UserItemView) : RecyclerView.ViewHolder(view)

class UserListAdapter : RecyclerView.Adapter<UsersCollectionViewHolder>() {

    var users = emptyList<User>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                UserListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersCollectionViewHolder {
        return UsersCollectionViewHolder(UserItemView(parent.context))
    }

    override fun onBindViewHolder(holder: UsersCollectionViewHolder, position: Int) {
        with(holder.view) {
            user = users[position]
        }
    }

    override fun getItemCount(): Int = users.size
}