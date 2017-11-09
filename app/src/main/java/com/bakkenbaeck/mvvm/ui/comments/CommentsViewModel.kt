package com.bakkenbaeck.mvvm.ui.comments

import android.arch.lifecycle.ViewModel
import android.content.Intent
import com.bakkenbaeck.mvvm.di.Modules
import com.bakkenbaeck.mvvm.ui.details.DetailsActivity
import com.bakkenbaeck.mvvm.util.Navigation


class CommentsViewModel internal constructor() : ViewModel(), CommentsNavigation {
    private val commentsRepo by lazy { Modules.provider.commentsRepo() }
    val comments by lazy { commentsRepo.getComments() }

    override fun onCommentClicked(clickedId: Int): Navigation<DetailsActivity> {
        val intent = Intent().putExtra(DetailsActivity.EXTRA_COMMENT_ID, clickedId)
        return Navigation(DetailsActivity::class.java, intent)
    }
}