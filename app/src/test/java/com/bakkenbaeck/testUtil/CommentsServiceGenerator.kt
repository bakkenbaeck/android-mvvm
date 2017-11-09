package com.bakkenbaeck.testUtil

import com.bakkenbaeck.mvvm.model.network.CommentsService
import com.bakkenbaeck.mvvm.model.network.MockResponseInterceptor
import okhttp3.OkHttpClient


class CommentsServiceGenerator(private val mockResponseFilename: String = "comments_200.json") {
    val commentsService: CommentsService by lazy { generate() }
    val httpClient: OkHttpClient by lazy { generateHttpClient() }

    private fun generate(): CommentsService {
        return CommentsService(httpClient)
    }

    private fun generateHttpClient(): OkHttpClient {
        val interceptor = MockResponseInterceptor(mockResponseFilename)
        return OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build()
    }
}