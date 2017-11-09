package com.bakkenbaeck.mvvm.ui.comments

import android.arch.lifecycle.ViewModel
import com.bakkenbaeck.mvvm.di.Modules


class CommentsViewModel internal constructor() : ViewModel() {

    companion object {
        const val TAG = "CommentsViewModel"
    }

    private val commentsRepo by lazy { Modules.provider.commentsRepo() }
    val comments by lazy { commentsRepo.getComments() }
}