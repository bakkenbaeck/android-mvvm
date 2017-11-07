package com.bakkenbaeck.mvvm.model.network

import com.bakkenbaeck.mvvm.model.data.Comment
import retrofit2.Call
import retrofit2.http.GET



interface NetworkInterface {
    @GET("comments")
    fun getComments(): Call<List<Comment>>
}
