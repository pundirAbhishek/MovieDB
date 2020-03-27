package com.android.moviedb.util

import com.android.moviedb.BuildConfig

const val API_KEY = BuildConfig.THE_MOVIE_DB_API_KEY
const val BASE_URL = "https://api.themoviedb.org/3/"
const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"
const val CATEGORY_POPULAR = "popular"
const val CATEGORY_UPCOMING = "upcoming"
const val CATEGORY_TOP_RATED = "top_rated"
const val SITE_YOUTUBE = "YouTube"
const val MOVIE_POSTER_BASE_URL = "http://image.tmdb.org/t/p/"
const val YOUTUBE_THUMBNAIL = "https://img.youtube.com/vi/%s/mqdefault.jpg";
const val YOUTUBE_BASE_URL = "http://www.youtube.com/watch?v="


fun getTrailersUrl(id: Long): String? {
    return "$BASE_URL$id/videos?api_key=$API_KEY"
}

fun getReviewsUrl(id: Long): String? {
    return "$BASE_URL$id/reviews?api_key=$API_KEY"
}