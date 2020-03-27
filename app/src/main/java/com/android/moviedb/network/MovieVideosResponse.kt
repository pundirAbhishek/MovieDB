package com.android.moviedb.network

import com.squareup.moshi.Json

data class MovieVideosResponse(
    val id: Int,
    @Json(name = "results") val videos: List<MovieVideo>
)