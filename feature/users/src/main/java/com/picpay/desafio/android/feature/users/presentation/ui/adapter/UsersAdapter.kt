package com.picpay.desafio.android.feature.users.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.feature.users.databinding.UsersCollectionViewItemBinding
import com.picpay.desafio.android.feature.users.databinding.UsersCollectionViewPlaceholderBinding
import com.picpay.desafio.android.feature.users.domain.model.UserVO
import com.picpay.desafio.android.feature.users.presentation.ui.UserPlaceholderViewHolder
import com.picpay.desafio.android.feature.users.presentation.ui.UserViewHolder
import com.picpay.desafio.core.ui.BaseViewHolder
import javax.inject.Inject

class UsersAdapter @Inject constructor() : RecyclerView.Adapter<BaseViewHolder<UserVO>>() {

    companion object {
        const val PLACEHOLDER = 0
        const val FILLED = 1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): com.picpay.desafio.core.ui.BaseViewHolder<UserVO> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            FILLED -> UserViewHolder(
                UsersCollectionViewItemBinding.inflate(inflater, parent, false)
            )
            PLACEHOLDER -> UserPlaceholderViewHolder(
                UsersCollectionViewPlaceholderBinding.inflate(inflater, parent, false)
            )
            else -> {
                throw NoSuchFieldException()
            }
        }
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<UserVO>,
        position: Int
    ) {
        holder.data = differ.currentList[position]
    }

    val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<UserVO>() {
        override fun areItemsTheSame(oldItem: UserVO, newItem: UserVO): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserVO, newItem: UserVO): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    })

    override fun getItemCount(): Int = differ.currentList.size

    override fun getItemViewType(position: Int): Int {
        return when (differ.currentList[position].isPlaceholder) {
            true -> PLACEHOLDER
            false -> FILLED
        }
    }
}