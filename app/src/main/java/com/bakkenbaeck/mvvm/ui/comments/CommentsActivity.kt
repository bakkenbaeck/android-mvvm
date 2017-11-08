package com.bakkenbaeck.mvvm.ui.comments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.bakkenbaeck.mvvm.R
import kotlinx.android.synthetic.main.activity_comments.*

class CommentsActivity : AppCompatActivity() {

    private lateinit var viewModel: CommentsViewModel
    private val commentsAdapter by lazy { CommentsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        initView()
        subscribeToComments()
    }

    fun initView() {
        initViewModel()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        comments.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            adapter = commentsAdapter
        }
    }

    private fun initViewModel() {
        this.viewModel = ViewModelProviders.of(this).get(CommentsViewModel::class.java)
    }

    private fun subscribeToComments() {
        this.viewModel.comments.observe(this, Observer {
            commentsAdapter.add(it ?: emptyList())
        })
    }
}