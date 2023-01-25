package com.picpay.desafio.android.users.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.picpay.desafio.android.core.ui.BaseActivity
import com.picpay.desafio.android.core.util.gone
import com.picpay.desafio.android.core.util.visible
import com.picpay.desafio.android.databinding.ActivityUsersBinding
import com.picpay.desafio.android.users.domain.UserVO
import com.picpay.desafio.android.users.presentation.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersActivity : BaseActivity() {

    private val viewModel: UserViewModel by viewModels()

    private lateinit var binding: ActivityUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupClickListeners()
        onViewState()
        viewModel.fetch()
    }

    private fun setupClickListeners() {
        with(binding) {
            usersErrorView.retryButton.setOnClickListener {
                viewModel.fetch()
            }
            with(usersSwipeLayout) {
                setOnRefreshListener {
                    viewModel.fetch()
                    isRefreshing = false
                }
            }
        }
    }

    override fun onViewState() {
        viewModel.state.observe(this) {
            when (it) {
                is UserViewModel.UsersViewState.OnFailure -> onError(it.message)
                is UserViewModel.UsersViewState.OnLoadUsers -> onLoadUsers(it.vos)
                is UserViewModel.UsersViewState.OnLoadPlaceholders -> onLoadUsers(it.vos)
            }
        }
    }

    private fun onLoadUsers(vos: List<UserVO>) {
        with(binding) {
            usersContentView.root.visible()
            usersErrorView.root.gone()
            usersContentView.usersCollectionView.setData(vos)
            if (vos.isEmpty()) {
                setupEmptyView()
            } else {
                setupFilledView()
            }
        }
    }

    private fun setupEmptyView() {
        with(binding.usersContentView) {
            usersEmptyLabel.visible()
            usersCollectionView.gone()
        }
    }

    private fun setupFilledView() {
        with(binding.usersContentView) {
            usersEmptyLabel.gone()
            usersCollectionView.visible()
        }
    }

    private fun onError(message: String) {
        with(binding.usersErrorView) {
            root.visible()
            errorMessageTextview.text = message
        }
        binding.usersContentView.root.gone()
    }
}
