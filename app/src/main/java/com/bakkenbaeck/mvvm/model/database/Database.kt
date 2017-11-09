package com.bakkenbaeck.mvvm.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.bakkenbaeck.mvvm.model.data.Comment

@Database(entities = arrayOf(Comment::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun commentDao(): CommentDao
}