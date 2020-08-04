package com.wsc.mvvmskeleton.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wsc.mvvmskeleton.data.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): PostDao
}