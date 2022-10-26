package com.example.androiddevelopment.ui.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevelopment.data.repository.NewsRepository
import com.example.androiddevelopment.ui.model.news.Article
import com.example.androiddevelopment.ui.model.news.NewsModel
import com.example.androiddevelopment.utils.ViewState
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
    private val insertNewsLiveData = MutableLiveData<ViewState<Boolean>>()

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

    fun insertNewsLiveData(article: Article) {
        compositeDisposable.add(
            newsRepository.insertNews(article)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Long>(){
                    override fun onSuccess(t: Long) {
                        insertNewsLiveData.value = ViewState.success(true)
                    }

                    override fun onError(e: Throwable) {
                        insertNewsLiveData.value = ViewState.error("Failed to insert data", null)
                    }

                })
        )
    }

    fun getVerifyApiResponseLiveData(): MutableLiveData<List<Article>> = listArticleLiveData

    fun getInsertNewsLiveData(): MutableLiveData<ViewState<Boolean>> = insertNewsLiveData

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}


