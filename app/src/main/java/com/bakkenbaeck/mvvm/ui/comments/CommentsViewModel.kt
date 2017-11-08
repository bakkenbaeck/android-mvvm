package com.bakkenbaeck.mvvm.ui.comments

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.bakkenbaeck.mvvm.model.data.Comment
import com.bakkenbaeck.mvvm.model.network.NetworkSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class CommentsViewModel : ViewModel() {


    private val subscriptions by lazy { CompositeDisposable() }

    val comments by lazy { MutableLiveData<List<Comment>>() }

    init {
        val disposable = NetworkSource()
                .getComments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comments::setValue)
        this.subscriptions.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        this.subscriptions.clear()
    }
}