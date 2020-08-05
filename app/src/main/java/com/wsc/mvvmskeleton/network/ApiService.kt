package com.wsc.mvvmskeleton.network

import com.wsc.mvvmskeleton.data.Post
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun postsWithCoroutines(): ApiResponse<ApiError, List<Post>>

    @GET("posts")
    //  fun postsWithRx(): ApiResponse<ApiError, List<Post>>
    fun postsWithRx(): Single<List<Post>>
}