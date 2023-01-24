package com.picpay.desafio.android.users.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.core.ui.BaseActivity
import com.picpay.desafio.android.core.util.gone
import com.picpay.desafio.android.core.util.visible
import com.picpay.desafio.android.databinding.ActivityUsersBinding
import com.picpay.desafio.android.users.domain.model.User
import com.picpay.desafio.android.users.presentation.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersActivity : BaseActivity() {

    private val viewModel: UserViewModel by viewModels()

    private val userAdapter = UserAdapter()

    private lateinit var binding: ActivityUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupClickListeners()
        onViewState()
        viewModel.fetch()
    }

    private fun setupView() {
        with(binding.usersContentView.recyclerView) {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupClickListeners() {
        binding.usersErrorView.retryButton.setOnClickListener {
            viewModel.fetch()
        }
    }

    override fun onViewState() {
        viewModel.state.observe(this) {
            when (it) {
                is UserViewModel.UsersViewState.OnLoadForms -> onLoadUsers(it.users)
                is UserViewModel.UsersViewState.OnFailure -> onError(it.message)
            }
        }
    }

    private fun onLoadUsers(users: List<User>) {
        binding.usersContentView.root.visible()
        binding.usersErrorView.root.gone()
        userAdapter.differ.submitList(users)
    }

    private fun onError(message: String) {
        with(binding.usersErrorView) {
            root.visible()
            errorMessageTextview.text = message
        }
        binding.usersContentView.root.gone()
    }
}
