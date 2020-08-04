package com.wsc.mvvmskeleton.network

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class ApiResponseCallAdapter<R>(
    private val responseType: Type
) : CallAdapter<R, Call<ApiResponse<ApiError, R>>> {

    override fun adapt(call: Call<R>): Call<ApiResponse<ApiError, R>> =
        ApiResponseCall(call, responseType)

    override fun responseType(): Type = responseType
}