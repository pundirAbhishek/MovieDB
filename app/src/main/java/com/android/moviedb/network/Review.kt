package com.android.moviedb.network

data class Review(
    val id: String,
    val author: String,
    val content: String,
    val url: String
)