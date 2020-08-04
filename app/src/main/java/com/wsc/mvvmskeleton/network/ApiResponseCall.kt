package com.wsc.mvvmskeleton.network

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type

class ApiResponseCall<R>(private val delegate: Call<R>, private val successType: Type) :
    Call<ApiResponse<ApiError, R>> {

    override fun isExecuted(): Boolean = delegate.isExecuted
    override fun clone(): Call<ApiResponse<ApiError, R>> =
        ApiResponseCall(delegate.clone(), successType)

    override fun isCanceled(): Boolean = delegate.isCanceled
    override fun cancel() = delegate.cancel()
    override fun execute(): Response<ApiResponse<ApiError, R>> =
        throw UnsupportedOperationException()

    override fun request(): Request = delegate.request()
    override fun timeout(): Timeout = delegate.timeout()

    override fun enqueue(callback: Callback<ApiResponse<ApiError, R>>) = delegate.enqueue(
        object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(
                    this@ApiResponseCall,
                    Response.success(response.toApiResponse())
                )
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                val error = when (throwable) {
                    is IOException -> ApiError.NetworkError(throwable)
                    else -> ApiError.UnknownApiError(throwable)
                }
                callback.onResponse(this@ApiResponseCall, Response.success(failure(error)))
            }

            private fun Response<R>.toApiResponse(): ApiResponse<ApiError, R> {
                val responseBody = body()
                return when {
                    !isSuccessful -> failure(ApiError.HttpError(code()))
                    responseBody != null -> success(responseBody)
                    else -> failure(ApiError.UnknownApiError(Throwable("")))
                }
            }
        })
}