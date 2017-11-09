package com.bakkenbaeck.mvvm.model.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.bakkenbaeck.mvvm.di.Modules
import com.bakkenbaeck.mvvm.model.data.Comment
import com.bakkenbaeck.mvvm.model.network.CommentsService




class CommentsRepository (
        private val webService: CommentsService = CommentsService()
) {
    private val scheduler by lazy { Modules.provider.scheduler() }
    private val commentDao by lazy { Modules.provider.database().commentDao() }

    fun getComments(): LiveData<List<Comment>> {
        val liveData = MutableLiveData<List<Comment>>()

        val cachedResults = getCachedResults()
        if (cachedResults.isNotEmpty()) {
            liveData.value = cachedResults
            return liveData
        }

        webService
                .getComments()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doOnSuccess(this::cacheComments)
                .subscribe(
                        liveData::setValue,
                        { Log.e("CommentsViewModel", "Error: $it") }
                )

        return liveData
    }

    private fun getCachedResults() = commentDao.getAllComments()

    private fun cacheComments(comments: List<Comment>) {
        commentDao.save(comments)
    }

    fun getComment(commentId: Int): LiveData<Comment> {
        val liveData = MutableLiveData<Comment>()
        val comment = commentDao.getComment(commentId)
        liveData.value = comment
        return liveData
    }
}