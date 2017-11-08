package com.bakkenbaeck.mvvm.ui.comments

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.bakkenbaeck.mvvm.di.Modules
import com.bakkenbaeck.mvvm.model.data.Comment
import io.reactivex.disposables.CompositeDisposable


class CommentsViewModel internal constructor() : ViewModel() {

    private val networkSource by lazy { Modules.provider.networkSource() }
    private val scheduler by lazy { Modules.provider.scheduler() }
    private val subscriptions by lazy { CompositeDisposable() }

    val comments by lazy { MutableLiveData<List<Comment>>() }

    init {
        val disposable = networkSource
                .getComments()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(comments::setValue)
        this.subscriptions.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        this.subscriptions.clear()
    }
}