package com.example.androiddevelopment.ui.model.news

data class NewsModel(
    var articles: List<Article>,
    var status: String,
    var totalResults: Int
)