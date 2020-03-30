package com.android.moviedb.network

import com.squareup.moshi.Json

data class TvSeriesDetailsResponse(


    val homepage: String,
    val id: Int,
    val popularity: Double,
    val overview: String,
    val status: String,
    val type: String,
    val name: String,
    @Json(name = "episode_run_time") val episodeRunTime: List<Int>,
    @Json(name = "number_of_episodes") val noOfEpisodes: Int,
    @Json(name = "no_of_seasons") val noOfSeasons: Int,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "last_air_date") val lastAirDate: String,
    @Json(name = "first_air_date") val releaseDate: String,
    @Json(name = "backdrop_path") val backdropPath: String?,


    // Appended Video, Reviews, Similar Movies , Credits(Cast, Crew) in Movie Details Request

    val videos: VideosResponse,
    val credits: CreditsResponse<Cast>,
    val reviews: PagedResponse<Review>,
    val similar: PagedResponse<TvSeries>


)