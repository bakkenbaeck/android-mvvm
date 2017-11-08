package com.bakkenbaeck.mvvm.model.network

import okhttp3.*
import java.io.IOException


class MockResponseInterceptor(
        private val filename: String
) : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val inputStream = javaClass.getResourceAsStream("/" + filename)
        val mimeType = "application/json"
        return Response.Builder()
                .addHeader("content-type", mimeType)
                .body(ResponseBody.create(MediaType.parse(mimeType), inputStream.readBytes()))
                .code(200)
                .message("Mock response from res/raw/" + filename)
                .protocol(Protocol.HTTP_1_0)
                .request(chain.request())
                .build()
    }
}