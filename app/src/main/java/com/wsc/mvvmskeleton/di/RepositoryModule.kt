package com.wsc.mvvmskeleton.di

import com.wsc.mvvmskeleton.ui.SearchRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single { SearchRepository(get(named(COROUTINES)), get(named(RX)), get()) }
}