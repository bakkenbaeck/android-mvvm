package com.bakkenbaeck.testUtil

import com.bakkenbaeck.mvvm.model.network.MockResponseInterceptor
import com.bakkenbaeck.mvvm.model.network.NetworkSource
import okhttp3.OkHttpClient


class NetworkSourceGenerator(private val mockResponseFilename: String = "comments_200.json") {
    val networkSource: NetworkSource by lazy { generate() }
    val httpClient: OkHttpClient by lazy { generateHttpClient() }

    private fun generate(): NetworkSource {
        return NetworkSource(httpClient)
    }

    private fun generateHttpClient(): OkHttpClient {
        val interceptor = MockResponseInterceptor(mockResponseFilename)
        return OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build()
    }
}