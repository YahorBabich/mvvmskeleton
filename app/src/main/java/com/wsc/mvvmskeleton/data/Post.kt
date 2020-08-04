package com.wsc.mvvmskeleton.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @ColumnInfo(name = "userId") val userId: Int,
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val body: String
)