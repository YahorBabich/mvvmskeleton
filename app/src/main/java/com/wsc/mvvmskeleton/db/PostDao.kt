package com.wsc.mvvmskeleton.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.wsc.mvvmskeleton.data.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    suspend fun getAll(): List<Post>

    @Query("SELECT * FROM post WHERE id IN (:ids)")
    suspend fun loadAllByIds(ids: IntArray): List<Post>

    @Query("SELECT * FROM post WHERE title LIKE :title LIMIT 1")
    suspend fun findByTitle(title: String): Post

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(vararg histories: Post)

    @Delete
    suspend fun delete(history: Post)

    @Query("DELETE FROM post")
    suspend fun deleteAll()
}