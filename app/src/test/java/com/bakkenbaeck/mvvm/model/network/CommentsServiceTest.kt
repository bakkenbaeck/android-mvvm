package com.bakkenbaeck.mvvm.model.network

import com.bakkenbaeck.mvvm.model.data.Comment
import com.bakkenbaeck.testUtil.CommentsServiceGenerator
import okhttp3.OkHttpClient
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test


class CommentsServiceTest {
    private lateinit var commentsService: CommentsService
    private lateinit var httpClient: OkHttpClient

    @Before
    fun setup() {
        val mockNetworkSource = CommentsServiceGenerator()
        commentsService = mockNetworkSource.commentsService
        httpClient = mockNetworkSource.httpClient
    }

    @Test
    fun getCommentsReturnsNonNullResponse() {
        val response: List<Comment> = commentsService.getComments().blockingGet()
        assertNotNull(response)
    }
}