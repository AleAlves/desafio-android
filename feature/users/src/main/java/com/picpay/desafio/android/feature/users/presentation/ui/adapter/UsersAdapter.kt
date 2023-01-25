package com.picpay.desafio.android.feature.users.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.feature.users.databinding.UsersCardViewHolderBinding
import com.picpay.desafio.android.feature.users.domain.model.UserVO
import com.picpay.desafio.android.feature.users.presentation.ui.UserViewHolder
import com.picpay.desafio.core.ui.BaseViewHolder
import javax.inject.Inject

class UsersAdapter @Inject constructor() : RecyclerView.Adapter<BaseViewHolder<UserVO>>() {

    val items = AsyncListDiffer(this, object : DiffUtil.ItemCallback<UserVO>() {
        override fun areItemsTheSame(oldItem: UserVO, newItem: UserVO): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserVO, newItem: UserVO): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    })

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<UserVO> {
        return UserViewHolder(
            UsersCardViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<UserVO>,
        position: Int
    ) {
        holder.data = items.currentList[position]
    }

    override fun getItemCount(): Int = items.currentList.size
}