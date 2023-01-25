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

class UsersCollectionView(
    context: Context,
    attributeSet: AttributeSet
) : LinearLayout(context, attributeSet) {

    private val adapter = UsersAdapter()
    private val layoutInflater: LayoutInflater get() = LayoutInflater.from(context)

    private var binding = UsersCollectionViewBinding.inflate(
        layoutInflater,
        this,
        true
    )

    fun setData(vos: List<UserVO>) {
        adapter.differ.submitList(vos)
    }

    init {
        binding.usersCollectionRecyclerview.layoutManager = LinearLayoutManager(context).apply {
            orientation = RecyclerView.VERTICAL
        }
        binding.usersCollectionRecyclerview.adapter = adapter
    }
}