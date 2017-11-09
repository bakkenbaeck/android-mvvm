package com.bakkenbaeck.mvvm.di

import com.bakkenbaeck.mvvm.model.database.Database
import com.bakkenbaeck.mvvm.model.repository.CommentsRepository
import com.bakkenbaeck.testUtil.CommentsServiceGenerator


class TestModules: ModuleProvider {
    override fun baseUrl() = "http://example.com/"
    override fun commentsRepo() = CommentsRepository(CommentsServiceGenerator().commentsService)
    override fun database(): Database {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun scheduler() = TestSchedulerProvider()
}