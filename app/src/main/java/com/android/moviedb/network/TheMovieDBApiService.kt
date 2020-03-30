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
    ): PagedResponse<Movie>

    // appendRequiredDetails string -> Should not Contain spaces
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int, @Query("api_key") api_key: String,
        @Query("append_to_response") appendRequiredDetails: String = "videos,reviews,similar,credits"
    ): MovieDetailsResponse

    // Discover movies by different types of data like average rating, number of votes, genres and certifications.
    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("sort_by") sortBy: String, @Query("api_key") api_key: String, @Query(
            "page"
        ) page: Int
    ): PagedResponse<Movie>

    @GET("search/movie")
    suspend fun getSearchMovies(
        @Query("query") query: String, @Query("api_key") api_key: String, @Query(
            "page"
        ) page: Int
    ): PagedResponse<Movie>

    /**
    to append more response add in the string with commas and should not contain any spaces
    movie_credits -> Gets the movie  the person has acted
    tv_credits -> Gets the movie TV shows the person has acted
     */
    @GET("person/{person_id}")
    suspend fun getPersonDetails(
        @Path("person_id") personId: Int, @Query("api_key") api_key: String,
        @Query("append_to_response") appendRequiredDetails: String = "movie_credits,tv_credits"
    ): PersonResponse

    @GET("tv/on_the_air")
    suspend fun getOnAirSeries(
        @Query("api_key") api_key: String, @Query("page") page: Int
    ): PagedResponse<TvSeries>

    @GET("tv/popular")
    suspend fun getPopularSeries(
        @Query("api_key") api_key: String, @Query("page") page: Int
    ): PagedResponse<TvSeries>

    @GET("tv/top_rated")
    suspend fun getTopRatedSeries(
        @Query("api_key") api_key: String, @Query("page") page: Int
    ): PagedResponse<TvSeries>

    // appendRequiredDetails string -> Should not Contain spaces
    @GET("tv/{tv_id}")
    suspend fun getSeriesDetails(
        @Path("tv_id") tvId: Int, @Query("api_key") api_key: String,
        @Query("append_to_response") appendRequiredDetails: String = "videos,reviews,similar,credits"
    ): TvSeriesDetailsResponse


//    @GET("movie/{movie_id}/similar")
//    suspend fun getSimilarMovies(
//        @Path("movie_id") category: String, @Query("api_key") api_key: String, @Query(
//            "page"
//        ) page: Int = 1
//    ): MoviesResponse

//    @GET("movie/{movie_id}/reviews")
//    suspend fun getMovieReviews(
//        @Path("movie_id") category: Int, @Query("api_key") api_key: String, @Query(
//            "page"
//        ) page: Int = 1
//    ): MovieReviewsResponse

//    @GET("movie/{movie_id}/videos")
//    suspend fun getMovieVideos(
//        @Path("movie_id") category: Int, @Query("api_key") api_key: String
//    ): MovieVideosResponse

//    @GET("movie/{movie_id}/credit")
//    suspend fun getCast(
//        @Path("movie_id") category: String, @Query("api_key") api_key: String
//    ): CastResponse

//    @GET("person/{person_id}/combined_credits")
//    suspend fun getPersonCredits(
//        @Path("person_id") personId: Int, @Query("api_key") api_key: String
//    ): PersonCredits
}
