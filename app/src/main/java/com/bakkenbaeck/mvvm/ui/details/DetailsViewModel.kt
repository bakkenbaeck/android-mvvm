package com.bakkenbaeck.mvvm.ui.details

import android.arch.lifecycle.ViewModel
import com.bakkenbaeck.mvvm.di.Modules

class DetailsViewModel internal constructor() : ViewModel() {

    private val commentsRepo by lazy { Modules.provider.commentsRepo() }

    fun getComment(commentId: Int) = commentsRepo.getComment(commentId)
}