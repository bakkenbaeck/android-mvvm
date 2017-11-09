package com.bakkenbaeck.mvvm.di

import com.bakkenbaeck.mvvm.model.repository.CommentsRepository
import com.bakkenbaeck.testUtil.CommentsServiceGenerator


class TestModules: ModuleProvider {

    override fun baseUrl() = "http://example.com/"
    override fun commentsRepo() = CommentsRepository(CommentsServiceGenerator().commentsService)
    override fun scheduler() = TestSchedulerProvider()
}