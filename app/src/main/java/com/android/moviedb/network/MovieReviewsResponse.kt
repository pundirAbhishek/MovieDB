package com.android.moviedb.network

import com.squareup.moshi.Json

data class MovieReviewsResponse(
    val id: Int,
    val page: Int,
    @Json(name = "results") val reviews: List<MovieReview>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)