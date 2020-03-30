package com.android.moviedb.network

import com.squareup.moshi.Json

data class PagedResponse<T>(
    val page: Int,
    @Json(name = "results") val results: List<T>,
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "total_pages") val totalPages: Int
)