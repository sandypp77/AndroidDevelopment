package com.example.androiddevelopment.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevelopment.data.repository.NewsRepository
import com.example.androiddevelopment.ui.model.news.Article
import com.example.androiddevelopment.ui.model.news.NewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel

class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val verifyApiResponseLiveData = MutableLiveData<NewsModel>()
    private val listArticleLiveData = MutableLiveData<List<Article>>()

    fun verifyApi(key: String) {
        compositeDisposable.add(
            newsRepository.verifyApi(key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<NewsModel>() {
                    override fun onSuccess(t: NewsModel) {
                        verifyApiResponseLiveData.value = t
                        listArticleLiveData.value = t.articles
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

    fun getVerifyApiResponseLiveData(): MutableLiveData<List<Article>> = listArticleLiveData

    override fun onCleared() {
        super.onCleared()
    }
}


