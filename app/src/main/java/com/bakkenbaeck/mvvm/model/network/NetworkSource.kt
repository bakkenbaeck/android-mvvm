package com.bakkenbaeck.mvvm.model.network

import android.content.Context
import com.bakkenbaeck.mvvm.R
import com.bakkenbaeck.mvvm.model.data.Comment
import com.bakkenbaeck.mvvm.ui.App
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
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
                .client(this.httpClient)
                .build()
        return retrofit.create(NetworkInterface::class.java)
    }

    fun getComments(): Call<List<Comment>> {
        return networkInterface.getComments()
    }
}