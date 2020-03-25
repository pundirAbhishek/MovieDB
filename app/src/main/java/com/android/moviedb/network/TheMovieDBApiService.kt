package com.android.moviedb.network

import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDBApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key: String): PopularMovie<Movie>
}
