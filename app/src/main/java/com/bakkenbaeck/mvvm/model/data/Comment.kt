package com.bakkenbaeck.mvvm.model.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "comment")
data class Comment(
        @PrimaryKey
        val id: Int,
        val postId: Int,
        val name: String,
        val email: String,
        val body: String
)