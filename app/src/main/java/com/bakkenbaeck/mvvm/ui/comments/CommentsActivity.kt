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
    private val commentsAdapter by lazy { CommentsAdapter({clickedId -> this.handleCommentClicked(clickedId)}) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        initView()
        subscribeToComments()
    }

    private fun initView() {
        initViewModel()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        comments.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = commentsAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(CommentsViewModel::class.java)
    }

    private fun subscribeToComments() {
        viewModel.comments.observe(this, Observer {
            commentsAdapter.add(it ?: emptyList())
        })
    }

    private fun handleCommentClicked(clickedId: Int) {
        val (clazz, intent) =  viewModel.onCommentClicked(clickedId)
        intent.setClass(this, clazz)
        startActivity(intent)
    }
}