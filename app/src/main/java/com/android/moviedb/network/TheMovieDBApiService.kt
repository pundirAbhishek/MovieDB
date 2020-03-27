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
    ): MoviesResponse<Movie>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") category: Int, @Query("api_key") api_key: String, @Query(
            "page"
        ) page: Int
    ): MovieReviewsResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") category: Int, @Query("api_key") api_key: String
    ): MovieVideosResponse
}
