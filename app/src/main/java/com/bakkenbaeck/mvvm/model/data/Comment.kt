package com.bakkenbaeck.mvvm.model.data


data class Comment(
        val postId: Int,
        val id: Int,
        val name: String,
        val email: String,
        val body: String
)