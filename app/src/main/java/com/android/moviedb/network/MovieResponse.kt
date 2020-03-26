package com.android.moviedb.network

import com.squareup.moshi.Json

data class MovieResponse<T>(
    val page: Int,
    val results: List<T>,
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "total_pages") val totalPages: Int
)