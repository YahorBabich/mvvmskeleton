package com.wsc.mvvmskeleton.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wsc.mvvmskeleton.network.ApiResponse
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    private val _data = MutableLiveData<Int>()
    val data: LiveData<Int> = _data

    private val disposables = CompositeDisposable()

    fun performWithCoroutines() {
        viewModelScope.launch {
            when (val apiResponse = repository.postsWithCoroutines()) {
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

    fun performWithRx() {
        disposables.add(repository.postsWithRx(onSuccess = {
            val list = it
            _data.value = list.size
        }, onError = {
            Log.e(TAG, "$it")
        }))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    companion object {
        val TAG = SearchViewModel::class.java.simpleName
    }
}