package com.android.moviedb.network

import com.squareup.moshi.Json

data class CastResponse(
    val cast: List<Cast>
)