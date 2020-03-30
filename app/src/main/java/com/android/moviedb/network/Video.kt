package com.android.moviedb.network

import com.android.moviedb.util.SITE_YOUTUBE
import com.squareup.moshi.Json
import java.util.*

data class Video(
    val id: String,
    val key: String,
    val name: String,
    val site: String,
    val type: String,
    @Json(name = "size") val quality: Int,
    @Json(name = "iso_639_1") val iso: String,
    @Json(name = "iso_3166_1") val iso_3166: String
){

    fun isYoutubeVideo(): Boolean {
        return site.toLowerCase(Locale.US) == SITE_YOUTUBE.toLowerCase(Locale.US)
    }

}