package com.wsc.mvvmskeleton.network

import com.wsc.mvvmskeleton.data.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun posts(): ApiResponse<ApiError, List<Post>>

    /*@GET("/search")
    fun search(@Query("term") term: String): ApiResponse<ApiError, KeywordSuggestions>*/
}