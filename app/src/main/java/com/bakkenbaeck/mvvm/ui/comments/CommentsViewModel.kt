package com.bakkenbaeck.mvvm.ui.comments

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.bakkenbaeck.mvvm.di.Modules
import com.bakkenbaeck.mvvm.model.data.Comment
import io.reactivex.disposables.CompositeDisposable


class CommentsViewModel internal constructor() : ViewModel() {

    companion object {
        const val TAG = "CommentsViewModel"
    }

    private val networkSource by lazy { Modules.provider.networkSource() }
    private val scheduler by lazy { Modules.provider.scheduler() }
    private val subscriptions by lazy { CompositeDisposable() }

    val comments by lazy { MutableLiveData<List<Comment>>() }

    init {
        getComments()
    }

    private fun getComments() {
        val disposable = networkSource
                .getComments()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(
                        comments::setValue,
                        { Log.e(TAG, "Error: $it") }
                )
        subscriptions.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}