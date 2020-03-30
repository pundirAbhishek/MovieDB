package com.android.moviedb.network

import com.squareup.moshi.Json

data class PersonResponse(
    val birthday: String?,
    val id: Int,
    val name: String,
    val gender: Int,
    val biography: String,
    val popularity: Double,
    val adult: Boolean,
    val homepage: String?,
    @Json(name = "also_known_as") val alsoKnownAs: List<String>,
    @Json(name = "known_for_department") val knownForDepartment: String,
    @Json(name = "deathday") val deathDay: String?,
    @Json(name = "place_of_birth") val placeOfBirth: String?,
    @Json(name = "profile_path") val profilePath: String?,
    @Json(name = "imdb_id") val imdbId: String,

    // Appended Tv Credits , Movie Credits in Person Details Request
    @Json(name = "movie_credits") val movieCredits: CreditsResponse<PersonMovieCredit>,
    @Json(name = "tv_credits") val tvCredits: CreditsResponse<PersonTvCredit>

)
