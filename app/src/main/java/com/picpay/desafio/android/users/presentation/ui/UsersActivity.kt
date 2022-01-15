package com.picpay.desafio.android.users.presentation.ui

import android.os.Bundle
import android.widget.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.core.ui.ViewState
import com.picpay.desafio.android.users.presentation.UserViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class UsersActivity : DaggerAppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var flipper: ViewFlipper
    private lateinit var error: TextView
    private lateinit var retry: ImageButton

    @Inject
    lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupObserver()
        setUpListeners()
        viewModel.fetch()
    }

    private fun setupView() {
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)
        flipper = findViewById(R.id.flipper)
        error = findViewById(R.id.textViewErrorMessage)
        retry = findViewById(R.id.buttonRetry)
        recyclerView.adapter = viewModel.adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObserver() {
        viewModel.state.observe(this, Observer {
            onViewStateChange(it)
        })
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun setUpListeners() {
        retry.setOnClickListener {
            viewModel.fetch()
        }
    }

    private fun onViewStateChange(viewState: ViewState) {
        flipper.displayedChild = viewState.state
    }
}
