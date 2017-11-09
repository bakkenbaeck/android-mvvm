package com.bakkenbaeck.mvvm.ui.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bakkenbaeck.mvvm.R
import com.bakkenbaeck.mvvm.model.data.Comment
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    companion object {
        val EXTRA_COMMENT_ID = "extra_comment_id"
    }

    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initViewModel()
        fetchComment()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
    }

    private fun fetchComment() {
        val commentId = intent.getIntExtra(EXTRA_COMMENT_ID, -1)
        viewModel.getComment(commentId).observe(this, Observer { renderComment(it) })
    }

    private fun renderComment(comment: Comment?) {
        name.text = comment?.name
        email.text = comment?.email
        body.text = comment?.body
    }
}
