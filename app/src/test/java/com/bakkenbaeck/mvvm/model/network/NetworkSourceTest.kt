package com.bakkenbaeck.mvvm.model.network

import android.content.Context
import okhttp3.OkHttpClient
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class NetworkSourceTest {

    private lateinit var context: Context

    @Before
    fun setup() {
        this.context = Mockito.mock(Context::class.java)
        Mockito.`when`(this.context.getString(Mockito.anyInt())).thenReturn("http://www.example.com")
    }

    @Test
    fun getCommentsReturnsNonNullResponse() {
        val networkSource = getNetworkSource("comments_200.json")
        val response = networkSource.getComments().execute().body()
        assertNotNull(response)
    }

    private fun getNetworkSource(mockedResponseFilename: String): NetworkSource {
        val httpClient = mockHttpClient(mockedResponseFilename)
        return NetworkSource(context, httpClient)
    }

    private fun mockHttpClient(mockedResponseFilename: String): OkHttpClient {
        val interceptor = MockResponseInterceptor(mockedResponseFilename)
        return OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build()
    }
}