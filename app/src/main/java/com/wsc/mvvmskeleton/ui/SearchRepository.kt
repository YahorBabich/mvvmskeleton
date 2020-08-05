package com.wsc.mvvmskeleton.ui

import com.wsc.mvvmskeleton.data.Post
import com.wsc.mvvmskeleton.db.PostDao
import com.wsc.mvvmskeleton.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchRepository(
    private val apiServiceWithCoroutines: ApiService,
    private val apiServiceWithRx: ApiService,
    private val dao: PostDao
) {

    suspend fun postsWithCoroutines() = apiServiceWithCoroutines.postsWithCoroutines()

    fun postsWithRx(
        onSuccess: (List<Post>) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return apiServiceWithRx.postsWithRx()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onSuccess, onError)
    }

    suspend fun insertAll(list: List<Post>) {
        dao.insertAll(*list.toTypedArray())
    }
}