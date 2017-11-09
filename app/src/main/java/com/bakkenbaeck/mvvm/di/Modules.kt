package com.bakkenbaeck.mvvm.di

import com.bakkenbaeck.mvvm.model.repository.CommentsRepository

object Modules {
    lateinit var provider: ModuleProvider

    fun init(provider: ModuleProvider = ProductionDependencies()) {
        this.provider = provider
    }
}

private class ProductionDependencies: ModuleProvider {
    override fun baseUrl() = "https://jsonplaceholder.typicode.com/"

    private val commentsRepo by lazy { CommentsRepository() }
    override fun commentsRepo() = commentsRepo

    private val schedulerProvider by lazy { AppSchedulerProvider() }
    override fun scheduler() = schedulerProvider
}

interface ModuleProvider {
    fun baseUrl(): String
    fun commentsRepo(): CommentsRepository
    fun scheduler(): SchedulerProvider
}