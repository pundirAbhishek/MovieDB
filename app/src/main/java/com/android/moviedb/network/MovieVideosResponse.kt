package com.android.moviedb.network

import com.squareup.moshi.Json

data class MovieVideosResponse(
    @Json(name = "results") val videos: List<MovieVideo>
)