package com.wsc.mvvmskeleton.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.wsc.mvvmskeleton.BuildConfig
import com.wsc.mvvmskeleton.network.ApiResponseCallAdapterFactory
import com.wsc.mvvmskeleton.network.ApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 5L
private const val BASE_URL: String = "https://jsonplaceholder.typicode.com/"

val networkModule = module {

    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(get<ApiResponseCallAdapterFactory>())
            .client(get())
            .build().create(ApiService::class.java)
    }

    single {
        OkHttpClient.Builder().apply {
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                addNetworkInterceptor(StethoInterceptor())
            }
        }.build()
    }

    single { ApiResponseCallAdapterFactory.create() }
}