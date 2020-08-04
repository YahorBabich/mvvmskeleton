package com.wsc.mvvmskeleton.network

sealed class ApiError {
    data class HttpError(val code: Int) : ApiError()
    data class NetworkError(val throwable: Throwable) : ApiError()
    data class UnknownApiError(val throwable: Throwable) : ApiError()
}