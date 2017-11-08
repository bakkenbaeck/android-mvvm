package com.bakkenbaeck.testUtil

import android.content.Context
import com.bakkenbaeck.mvvm.model.network.MockResponseInterceptor
import com.bakkenbaeck.mvvm.model.network.NetworkSource
import okhttp3.OkHttpClient
import org.mockito.Mockito


class NetworkSourceGenerator(private val mockResponseFilename: String = "comments_200.json") {

    private val context: Context by lazy { mockContext() }
    val networkSource: NetworkSource by lazy { generate() }
    val httpClient: OkHttpClient by lazy { generateHttpClient() }

    private fun generate(): NetworkSource {
        return NetworkSource(context, httpClient)
    }

    private fun generateHttpClient(): OkHttpClient {
        val interceptor = MockResponseInterceptor(mockResponseFilename)
        return OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build()
    }

    private fun mockContext(): Context {
        val mockContext = Mockito.mock(Context::class.java)
        Mockito.`when`(mockContext.getString(Mockito.anyInt())).thenReturn("http://www.example.com")
        return mockContext
    }
}