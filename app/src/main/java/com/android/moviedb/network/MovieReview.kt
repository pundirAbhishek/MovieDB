package com.android.moviedb.network

data class MovieReview(
    val id: String,
    val author: String,
    val content: String,
    val url: String
)