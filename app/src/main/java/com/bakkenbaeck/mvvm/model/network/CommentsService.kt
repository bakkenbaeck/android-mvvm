package com.bakkenbaeck.mvvm.model.network

import com.bakkenbaeck.mvvm.di.Modules
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class CommentsService(
        private val httpClient: OkHttpClient = OkHttpClient()
) {

    private val networkInterface by lazy { initNetworkInterface() }

    private fun initNetworkInterface(): CommentsInterface {
        val retrofit = Retrofit.Builder()
                .baseUrl(Modules.provider.baseUrl())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
        return retrofit.create(CommentsInterface::class.java)
    }

    fun getComments() = networkInterface.getComments()
}