package com.bakkenbaeck.mvvm.di

import android.arch.persistence.room.Room
import com.bakkenbaeck.mvvm.model.database.Database
import com.bakkenbaeck.mvvm.model.repository.CommentsRepository
import com.bakkenbaeck.mvvm.ui.App

object Modules {
    lateinit var provider: ModuleProvider

    fun init(provider: ModuleProvider = ProductionDependencies()) {
        this.provider = provider
    }
}

private class ProductionDependencies() : ModuleProvider {

    override fun baseUrl() = "https://jsonplaceholder.typicode.com/"

    private val commentsRepo by lazy { CommentsRepository() }
    override fun commentsRepo() = commentsRepo

    private val database by lazy { Room.databaseBuilder(App.context(), Database::class.java, "db").allowMainThreadQueries().build()}
    override fun database() = database

    private val schedulerProvider by lazy { AppSchedulerProvider() }
    override fun scheduler() = schedulerProvider
}

interface ModuleProvider {
    fun baseUrl(): String
    fun commentsRepo(): CommentsRepository
    fun database(): Database
    fun scheduler(): SchedulerProvider
}