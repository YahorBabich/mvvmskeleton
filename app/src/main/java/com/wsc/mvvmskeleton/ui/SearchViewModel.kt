package com.wsc.mvvmskeleton.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wsc.mvvmskeleton.network.ApiResponse
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    fun perform() {
        viewModelScope.launch {
            when (val apiResponse = repository.posts()) {
                is ApiResponse.Success -> {
                    val list = apiResponse.response
                    Log.d("asasd", "sdsfd")
                }
                is ApiResponse.Failure -> {
                    Log.e(TAG, "${apiResponse.error}")
                }
            }
        }
    }

    companion object {
        val TAG = SearchViewModel::class.java.simpleName
    }
}