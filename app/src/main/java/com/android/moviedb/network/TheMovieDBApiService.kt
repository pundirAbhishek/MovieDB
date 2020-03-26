package com.android.moviedb.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBApiService {

    @GET("movie/{category}")
    suspend fun getMovies(
        @Path("category") category: String, @Query("api_key") api_key: String, @Query(
            "page"
        ) page: Int
    ): MovieResponse<Movie>
}
