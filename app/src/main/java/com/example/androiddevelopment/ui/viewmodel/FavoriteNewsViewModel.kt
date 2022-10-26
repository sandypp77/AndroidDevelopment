package com.example.androiddevelopment.ui.viewmodel

import android.os.Bundle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevelopment.data.repository.NewsRepository
import com.example.androiddevelopment.ui.model.news.Article
import com.example.androiddevelopment.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

@HiltViewModel
class FavoriteNewsViewModel @Inject constructor(private val newsRepository: NewsRepository) :
    ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val favoriteNewsLiveData = MutableLiveData<ViewState<List<Article>>>()

    fun getFavoriteNews() {
        compositeDisposable.add(
            newsRepository.getFavoriteNews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSubscriber<List<Article>>() {
                    override fun onNext(t: List<Article>?) {
                        t?.let {
                            if (t.isNotEmpty()) {
                                favoriteNewsLiveData.value = ViewState.success(t)
                            } else {
                                favoriteNewsLiveData.value = ViewState.empty("No data found")
                            }
                        }
                    }

                    override fun onError(t: Throwable?) {
                        t?.message?.let {
                            favoriteNewsLiveData.value = ViewState.error(it, null)
                        }
                    }

                    override fun onComplete() {

                    }

                })
        )
    }

    fun getFavoriteNewsLiveData(): MutableLiveData<ViewState<List<Article>>> = favoriteNewsLiveData

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}