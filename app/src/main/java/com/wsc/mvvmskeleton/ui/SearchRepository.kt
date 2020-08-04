package com.wsc.mvvmskeleton.ui

import com.wsc.mvvmskeleton.data.Post
import com.wsc.mvvmskeleton.db.PostDao
import com.wsc.mvvmskeleton.network.ApiService

class SearchRepository(private val apiService: ApiService, private val dao: PostDao) {

    suspend fun posts() = apiService.posts()

    suspend fun insertAll(list: List<Post>) {
        dao.insertAll(*list.toTypedArray())
    }
}