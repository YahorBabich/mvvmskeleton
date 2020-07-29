package com.wsc.mvvmskeleton.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    fun perform(symbol: String) {
        _data.value = symbol
    }
}