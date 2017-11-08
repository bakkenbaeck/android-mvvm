package com.bakkenbaeck.mvvm.model.network

import android.content.Context
import com.bakkenbaeck.mvvm.R
import com.bakkenbaeck.mvvm.model.data.Comment
import com.bakkenbaeck.mvvm.ui.App
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class NetworkSource(
        private val context: Context = App.context(),
        private val httpClient: OkHttpClient = OkHttpClient()
) {

    private val networkInterface by lazy { initNetworkInterface() }

    private fun initNetworkInterface(): NetworkInterface {
        val retrofit = Retrofit.Builder()
                .baseUrl(context.getString(R.string.baseUrl))
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(this.httpClient)
                .build()
        return retrofit.create(NetworkInterface::class.java)
    }

    fun getComments(): Single<List<Comment>> {
        return networkInterface.getComments()
    }
}