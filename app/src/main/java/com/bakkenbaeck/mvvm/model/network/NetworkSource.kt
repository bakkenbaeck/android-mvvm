package com.bakkenbaeck.mvvm.model.network

import android.content.Context
import com.bakkenbaeck.mvvm.R
import com.bakkenbaeck.mvvm.di.Modules
import com.bakkenbaeck.mvvm.ui.App
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class NetworkSource(
        private val httpClient: OkHttpClient = OkHttpClient()
) {

    private val networkInterface by lazy { initNetworkInterface() }

    private fun initNetworkInterface(): NetworkInterface {
        val retrofit = Retrofit.Builder()
                .baseUrl(Modules.provider.baseUrl())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
        return retrofit.create(NetworkInterface::class.java)
    }

    fun getComments() = networkInterface.getComments()
}