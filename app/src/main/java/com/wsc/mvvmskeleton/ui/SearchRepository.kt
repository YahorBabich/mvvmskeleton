package com.wsc.mvvmskeleton.ui

import com.wsc.mvvmskeleton.network.ApiService

class SearchRepository(private val apiService: ApiService) {

    suspend fun posts() = apiService.posts()
}