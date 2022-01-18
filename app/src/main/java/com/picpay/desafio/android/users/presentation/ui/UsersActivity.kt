package com.picpay.desafio.android.users.presentation.ui

import android.os.Bundle
import android.widget.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.core.ui.ViewState
import com.picpay.desafio.android.databinding.ActivityUsersBinding
import com.picpay.desafio.android.users.presentation.UserViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class UsersActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: UserViewModel

    lateinit var binding: ActivityUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupObserver()
        setUpListeners()
        viewModel.fetch()
    }

    private fun setupView() {
        binding.normal.recyclerView.adapter = viewModel.adapter
        binding.normal.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObserver() {
        viewModel.state.observe(this, {
            onViewStateChange(it)
        })
        viewModel.error.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun setUpListeners() {
        binding.error.buttonRetry.setOnClickListener {
            viewModel.fetch()
        }
    }

    private fun onViewStateChange(viewState: ViewState) {
        binding.flipper.displayedChild = viewState.state
    }
}
