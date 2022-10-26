package com.example.androiddevelopment.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.androiddevelopment.room.model.NewsEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface NewsDao {
    @Query("select * from article")
    fun getFavoriteNews(): Flowable<List<NewsEntity>>

    @Insert
    fun insertNews(newsEntity: NewsEntity): Single<Long>
}