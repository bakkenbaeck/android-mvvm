package com.bakkenbaeck.mvvm.model.database

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.bakkenbaeck.mvvm.model.data.Comment
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test


class CommentDaoTest {
    private lateinit var commentDao: CommentDao
    private lateinit var db: Database

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, Database::class.java).build()
        commentDao = db.commentDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun canInsertCommentsWithoutException() {
        val comments = generateComments(2)
        commentDao.save(comments)
    }

    @Test
    fun canReadBackInsertedComments() {
        val expectedNumComments = 5
        val comments = generateComments(expectedNumComments)
        commentDao.save(comments)
        val cachedResults = commentDao.getAllComments()
        assertThat(cachedResults.size, `is`(expectedNumComments))
    }

    @Test
    fun commentsWithSameIdOverwriteEachOther() {
        val comment = Comment(1,123,"name", "email", "body")
        commentDao.save(listOf(comment))
        commentDao.save(listOf(comment))
        val cachedResults = commentDao.getAllComments()
        assertThat(cachedResults.size, `is`(1))
    }

    @Test
    fun commentWithSameIdOverwritesOldComment() {
        val originalBody = "body"
        val expectedBody = originalBody + "updated"
        val originalComment = Comment(1,123,"name", "email", originalBody)
        val updatedComment  = Comment(1,123,"name", "email", expectedBody)
        commentDao.save(listOf(originalComment))
        commentDao.save(listOf(updatedComment))
        val cachedResults = commentDao.getAllComments()
        assertThat(cachedResults[0].body, `is`(expectedBody))
    }

    private fun generateComments(numberOfComments: Int) = (1..numberOfComments).map { Comment(it, 2, "Name", "email", "body") }
}