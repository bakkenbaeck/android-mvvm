package com.bakkenbaeck.mvvm.model.network

import com.bakkenbaeck.mvvm.model.data.Comment
import com.bakkenbaeck.testUtil.NetworkSourceGenerator
import okhttp3.OkHttpClient
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test


class NetworkSourceTest {
    private lateinit var networkSource : NetworkSource
    private lateinit var httpClient: OkHttpClient

    @Before
    fun setup() {
        val mockNetworkSource = NetworkSourceGenerator()
        networkSource = mockNetworkSource.networkSource
        httpClient = mockNetworkSource.httpClient
    }

    @Test
    fun getCommentsReturnsNonNullResponse() {
        val response: List<Comment> = networkSource.getComments().blockingGet()
        assertNotNull(response)
    }


}