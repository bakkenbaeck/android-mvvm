package com.bakkenbaeck.mvvm.model.network

import com.bakkenbaeck.mvvm.model.data.Comment
import io.reactivex.Single
import retrofit2.http.GET


interface NetworkInterface {
    @GET("comments")
    fun getComments(): Single<List<Comment>>
}
