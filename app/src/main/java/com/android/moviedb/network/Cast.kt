package com.android.moviedb.network

import com.squareup.moshi.Json

data class Cast(
    val id: Int,
    val character: String,
    val name: String,
    val order: Int,
    val gender: Int?,
    @Json(name = "profile_path") val profilePath: String?
)