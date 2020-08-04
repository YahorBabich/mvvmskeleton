package com.wsc.mvvmskeleton.network

sealed class ApiResponse<out F : ApiError, out S> {
    data class Failure<out F : ApiError>(val error: F) : ApiResponse<F, Nothing>()
    data class Success<out S>(val response: S) : ApiResponse<Nothing, S>()
}

fun <F : ApiError> failure(error: F): ApiResponse<F, Nothing> = ApiResponse.Failure(error)
fun <S> success(response: S): ApiResponse<Nothing, S> = ApiResponse.Success(response)
