package com.wsc.mvvmskeleton.network

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiResponseCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type, annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        if (getRawType(returnType) != Call::class.java) {
            return null
        }
        check(returnType is ParameterizedType)

        val responseType = getParameterUpperBound(0, returnType)
        check(responseType is ParameterizedType)
        if (getRawType(responseType) != ApiResponse::class.java) {
            return null
        }
        val apiError = getParameterUpperBound(0, responseType)
        if (getRawType(apiError) != ApiError::class.java) {
            return null
        }
        val success = getParameterUpperBound(1, responseType)
        return ApiResponseCallAdapter<Any>(success)
    }

    companion object {
        @JvmStatic
        fun create() = ApiResponseCallAdapterFactory()
    }
}