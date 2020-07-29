package com.wsc.mvvmskeleton.di

import com.wsc.mvvmskeleton.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchViewModel() }
}