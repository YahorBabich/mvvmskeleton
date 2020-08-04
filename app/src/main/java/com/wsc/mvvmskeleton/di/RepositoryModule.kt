package com.wsc.mvvmskeleton.di

import com.wsc.mvvmskeleton.ui.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { SearchRepository(get()) }
}