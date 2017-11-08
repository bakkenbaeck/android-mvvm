package com.bakkenbaeck.mvvm.di

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


interface SchedulerProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
}

class AppSchedulerProvider : SchedulerProvider {
    override fun ui() = AndroidSchedulers.mainThread()

    override fun io() = Schedulers.io()
}