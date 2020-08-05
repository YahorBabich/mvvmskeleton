package com.wsc.mvvmskeleton.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.wsc.mvvmskeleton.BuildConfig
import com.wsc.mvvmskeleton.network.ApiResponseCallAdapterFactory
import com.wsc.mvvmskeleton.network.ApiService
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

private const val TIMEOUT = 5L
private const val BASE_URL: String = "https://jsonplaceholder.typicode.com/"

internal const val RX = "RX"
internal const val COROUTINES = "COROUTINES"

val networkModule = module {

    single<ApiService>(named(COROUTINES)) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(get<ApiResponseCallAdapterFactory>())
            .client(get())
            .build().create(ApiService::class.java)
    }

    single<ApiService>(named(RX)) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
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