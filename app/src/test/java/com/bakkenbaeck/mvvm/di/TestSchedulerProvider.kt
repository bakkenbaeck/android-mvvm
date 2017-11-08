package com.bakkenbaeck.mvvm.di

import io.reactivex.schedulers.Schedulers


class TestSchedulerProvider : SchedulerProvider {
    override fun ui() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
}