package com.android.moviedb.network

import com.squareup.moshi.Json

data class PersonTvCredit(
    val id: Int,
    val name: String,
    val overview: String,
    val character: String,
    val popularity: Double,
    @Json(name = "credit_id") val creditId: String,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "episode_count") val episodeCount: Int,
    @Json(name = "origin_country") val originCountry: List<String>,
    @Json(name = "original_name") val originalName: String,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "first_air_date") val firstAirDate: String,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?

)