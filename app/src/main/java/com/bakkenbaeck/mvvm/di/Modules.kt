package com.bakkenbaeck.mvvm.di

import com.bakkenbaeck.mvvm.model.network.NetworkSource

object Modules {
    lateinit var provider: ModuleProvider

    fun init(provider: ModuleProvider = ProductionDependencies()) {
        this.provider = provider
    }
}

private class ProductionDependencies: ModuleProvider {
    private val networkSource by lazy { NetworkSource() }
    override fun networkSource() = networkSource

    private val schedulerProvider by lazy { AppSchedulerProvider() }
    override fun scheduler() = schedulerProvider
}

interface ModuleProvider {
    fun networkSource(): NetworkSource
    fun scheduler(): SchedulerProvider
}