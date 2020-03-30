package com.android.moviedb.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvSeries(
    val popularity: Double,
    val id: Int,
    val overview: String,
    val name: String,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "first_air_date") val releaseDate: String,
    @Json(name = "origin_country") val originCountry: List<String>,
    @Json(name = "original_name") val originalName: String

) : Parcelable