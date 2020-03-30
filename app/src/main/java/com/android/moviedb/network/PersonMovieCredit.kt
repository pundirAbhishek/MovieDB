package com.android.moviedb.network

import com.squareup.moshi.Json

data class PersonMovieCredit(
    val id: Int,
    val overview: String,
    val video: Boolean,
    val adult: Boolean,
    val title: String,
    val character: String,
    val popularity: Double,
    @Json(name = "credit_id") val creditId: String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?
)