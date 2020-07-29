package com.wsc.mvvmskeleton

import android.app.Application
import com.wsc.mvvmskeleton.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SkeletonApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SkeletonApplication)
            modules(
                listOf(viewModelModule)
            )
        }
    }
}