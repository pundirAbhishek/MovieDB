package com.android.moviedb.network

import com.squareup.moshi.Json


data class CreditsResponse<T>(
    val cast: List<T>
)