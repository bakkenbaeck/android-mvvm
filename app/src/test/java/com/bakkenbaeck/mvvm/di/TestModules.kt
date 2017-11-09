package com.bakkenbaeck.mvvm.di

import com.bakkenbaeck.mvvm.model.database.CommentDao
import com.bakkenbaeck.mvvm.model.database.Database
import com.bakkenbaeck.mvvm.model.repository.CommentsRepository
import com.bakkenbaeck.testUtil.CommentsServiceGenerator
import org.mockito.Mockito


class TestModules: ModuleProvider {
    override fun baseUrl() = "http://example.com/"
    override fun commentsRepo() = CommentsRepository(CommentsServiceGenerator().commentsService)
    override fun database() = mockDatabase()
    override fun scheduler() = TestSchedulerProvider()

    private fun mockDatabase(): Database {
        val commentDao = Mockito.mock(CommentDao::class.java)
        Mockito.`when`(commentDao.getAllComments()).thenReturn(emptyList())
        val db = Mockito.mock(Database::class.java)
        Mockito.`when`(db.commentDao()).thenReturn(commentDao)
        return db
    }
}