package com.bakkenbaeck.mvvm.model.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.bakkenbaeck.mvvm.model.data.Comment


@Dao interface CommentDao {
    @Query("select * from comment")
    fun getAllComments(): List<Comment>

    @Insert(onConflict = REPLACE)
    fun save(comments: List<Comment>)

    @Query("SELECT * FROM comment where id = :commentId")
    fun getComment(commentId: Int): Comment?
}