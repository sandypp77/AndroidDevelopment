package com.example.androiddevelopment.di

import android.content.Context
import androidx.room.Room
import com.example.androiddevelopment.data.local.NewsLocalDataSource
import com.example.androiddevelopment.data.remote.NewsRemoteDataSource
import com.example.androiddevelopment.data.repository.NewsRepository
import com.example.androiddevelopment.data.service.NewsService
import com.example.androiddevelopment.retrofit.NewsRetrofit
import com.example.androiddevelopment.room.NewsDao
import com.example.androiddevelopment.room.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NewsModule {
    @Provides
    @Singleton
    fun provideNewsService(): NewsService = NewsRetrofit.newsService

    @Provides
    @Singleton
    fun provideNewsRemoteDataSource(newsService: NewsService): NewsRemoteDataSource =
        NewsRemoteDataSource(newsService)

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource
    ): NewsRepository =
        NewsRepository(newsRemoteDataSource, newsLocalDataSource)

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext appContext: Context): NewsDatabase =
        Room.databaseBuilder(appContext, NewsDatabase::class.java, "newsDatabase").build()

    @Provides
    @Singleton
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao = newsDatabase.newsDao()

    @Provides
    @Singleton
    fun provideNewsLocalDataSource(newsDao: NewsDao): NewsLocalDataSource =
        NewsLocalDataSource(newsDao)
}