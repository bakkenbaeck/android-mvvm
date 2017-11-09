package com.bakkenbaeck.mvvm.model.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.bakkenbaeck.mvvm.di.Modules
import com.bakkenbaeck.mvvm.model.data.Comment
import com.bakkenbaeck.mvvm.model.network.CommentsService
import com.bakkenbaeck.mvvm.ui.comments.CommentsViewModel




class CommentsRepository (
        private val webService: CommentsService = CommentsService()
) {
    private val scheduler by lazy { Modules.provider.scheduler() }

    fun getComments(): LiveData<List<Comment>> {
        val liveData = MutableLiveData<List<Comment>>()
        webService
                .getComments()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(
                        liveData::setValue,
                        { Log.e(CommentsViewModel.TAG, "Error: $it") }
                )

        return liveData
    }
}