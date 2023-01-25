package com.picpay.desafio.android.users.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.core.ui.BaseViewHolder
import com.picpay.desafio.android.databinding.UsersCollectionViewItemBinding
import com.picpay.desafio.android.databinding.UsersCollectionViewPlaceholderBinding
import com.picpay.desafio.android.users.domain.UserVO
import javax.inject.Inject

class UserAdapter @Inject constructor() : RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        const val PLACEHOLDER = 0
        const val FILLED = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
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

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
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