package com.android.moviedb.ui

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.moviedb.BuildConfig
import com.android.moviedb.network.*
import com.android.moviedb.util.API_KEY
import com.android.moviedb.util.CATEGORY_UPCOMING
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class DetailViewModel(movie: Movie) : ViewModel() {

    // The internal MutableLiveData For Selected Movie that stores the  detailed movie
    private val _selectedMovie = MutableLiveData<Movie>()
    // The external immutable LiveData for the Selected Movie
    val selectedMovie: LiveData<Movie>
        get() = _selectedMovie

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<MovieDBApiStatus>()
    // The external immutable LiveData for the request status String
    val status: LiveData<MovieDBApiStatus>
        get() = _status

    // The internal MutableLiveData MovieVideos that stores the result of the request
    private val _movieVideos = MutableLiveData<MovieVideosResponse>()
    // The external immutable LiveData for the request MovieVideos
    val movieVideos: LiveData<MovieVideosResponse>
        get() = _movieVideos

    // The internal MutableLiveData MovieReviews that stores the result of the request
    private val _movieReviews = MutableLiveData<MovieReviewsResponse>()
    // The external immutable LiveData for the request MovieReviews
    val movieReviews: LiveData<MovieReviewsResponse>
        get() = _movieReviews

    // Play Video in Youtube
    private val _intentToPlayVideo = MutableLiveData<MovieVideo>()
    val intentToPlayVideo: LiveData<MovieVideo>
        get() = _intentToPlayVideo

    // View Review through url
    private val _intentToViewReview = MutableLiveData<MovieReview>()
    val intentToViewReview: LiveData<MovieReview>
        get() = _intentToViewReview


    init {
        _selectedMovie.value = movie
        launchMovieVideosRequest()
        launchMovieReviewsRequest()
    }

    private fun launchMovieVideosRequest() {
        viewModelScope.launch {
            try {
                _status.value = MovieDBApiStatus.LOADING
                val videos = getMovieVideos()
                _status.value = MovieDBApiStatus.DONE
                _movieVideos.value = videos
                Timber.i("Top Rated %s", videos.toString())
            } catch (t: Throwable) {
                _status.value = MovieDBApiStatus.ERROR
                Timber.i(t)
            }

        }
    }

    private fun launchMovieReviewsRequest() {
        viewModelScope.launch {
            try {
                _status.value = MovieDBApiStatus.LOADING
                val reviews = getMovieReviews()
                _status.value = MovieDBApiStatus.DONE
                _movieReviews.value = reviews
                Timber.i("Top Rated %s", reviews.toString())
            } catch (t: Throwable) {
                _status.value = MovieDBApiStatus.ERROR
                Timber.i(t)
            }

        }
    }


    private suspend fun getMovieVideos() = withContext(Dispatchers.IO) {
        Timber.i("Get Movie Videos")
        TheMovieDBApi.retrofitService.getMovieVideos(_selectedMovie.value!!.id, API_KEY)

    }

    private suspend fun getMovieReviews(page: Int = 1) = withContext(Dispatchers.IO) {
        Timber.i("Get Movie Reviews")
        TheMovieDBApi.retrofitService.getMovieReviews(_selectedMovie.value!!.id, API_KEY, page)
    }

    fun intentToPlayVideo(video: MovieVideo) {
        _intentToPlayVideo.value = video
    }

    fun intentToPlayVideoCompleted() {
        _intentToPlayVideo.value = null
    }

    fun intentToViewReview(review: MovieReview) {
        _intentToViewReview.value = review
    }

    fun intentToViewReviewCompleted() {
        _intentToViewReview.value = null
    }

}