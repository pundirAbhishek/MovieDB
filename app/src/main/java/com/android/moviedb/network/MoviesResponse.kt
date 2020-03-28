package com.android.moviedb.network

import com.squareup.moshi.Json

data class MoviesResponse(
    val page: Int,
    @Json(name = "results")val movies: List<Movie>,
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "total_pages") val totalPages: Int
)