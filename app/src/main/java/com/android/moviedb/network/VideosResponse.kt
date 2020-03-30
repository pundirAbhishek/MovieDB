package com.android.moviedb.network

import com.squareup.moshi.Json

data class VideosResponse(
    @Json(name = "results") val videos: List<Video>
)