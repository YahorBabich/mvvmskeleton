package com.wsc.mvvmskeleton.ui

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wsc.mvvmskeleton.network.ApiResponse
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    private val _data = MutableLiveData<Int>()
    val data: LiveData<Int> = _data

    fun perform() {
        viewModelScope.launch {
            when (val apiResponse = repository.posts()) {
                is ApiResponse.Success -> {
                    val list = apiResponse.response
                    repository.insertAll(list)
                    _data.value = list.size
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