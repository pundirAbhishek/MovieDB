package com.android.moviedb.network

import com.squareup.moshi.Json

data class MovieDetails(
    val adult: Boolean,
    val popularity: Double,
    val budget: Int,
    val video: Boolean,
    val id: Int,
    val revenue: Int,
    val runtime: Int?,
    val title: String,
    val overview: String,
    @Json(name = "imdb_id") val imdbId: Int?,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "release_date") val releaseDate: String
)