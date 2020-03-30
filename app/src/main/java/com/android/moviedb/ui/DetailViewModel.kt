package com.android.moviedb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.moviedb.network.*
import com.android.moviedb.util.API_KEY
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
    private val _movieVideos = MutableLiveData<VideosResponse>()
    // The external immutable LiveData for the request MovieVideos
    val movieVideos: LiveData<VideosResponse>
        get() = _movieVideos

    // The internal MutableLiveData MovieReviews that stores the result of the request
    private val _movieReviews = MutableLiveData<PagedResponse<Review>>()
    // The external immutable LiveData for the request MovieReviews
    val movieReviews: LiveData<PagedResponse<Review>>
        get() = _movieReviews

    // Play Video in Youtube
    private val _intentToPlayVideo = MutableLiveData<Video>()
    val intentToPlayVideo: LiveData<Video>
        get() = _intentToPlayVideo

    // View Review through url
    private val _intentToViewReview = MutableLiveData<Review>()
    val intentToViewReview: LiveData<Review>
        get() = _intentToViewReview

    // The internal MutableLiveData MovieDetails that stores the result of the request
    private val _movieDetails = MutableLiveData<MovieDetailsResponse>()
    // The external immutable LiveData for the request MovieDetails
    val movieDetails: LiveData<MovieDetailsResponse>
        get() = _movieDetails

    // The internal MutableLiveData MovieDetails that stores the result of the request
    private val _movieCast = MutableLiveData<CreditsResponse<Cast>>()
    // The external immutable LiveData for the request MovieDetails
    val movieCast: LiveData<CreditsResponse<Cast>>
        get() = _movieCast

    // The internal MutableLiveData MovieDetails that stores the result of the request
    private val _similarMovies = MutableLiveData<PagedResponse<Movie>>()
    // The external immutable LiveData for the request MovieDetails
    val similarMovies: LiveData<PagedResponse<Movie>>
        get() = _similarMovies


    init {
        _selectedMovie.value = movie
//        launchMovieVideosRequest()
//        launchMovieReviewsRequest()

        launchMovieDetailsRequest()
    }

    private fun launchMovieDetailsRequest() {
        viewModelScope.launch {
            try {
                _status.value = MovieDBApiStatus.LOADING
                val movieDetails = getMovieDetails()
                _status.value = MovieDBApiStatus.DONE
                _movieDetails.value = movieDetails
                _movieReviews.value = _movieDetails.value!!.reviews
                _movieVideos.value = _movieDetails.value!!.videos
                _movieCast.value = _movieDetails.value!!.credits
                _similarMovies.value = _movieDetails.value!!.similar
                Timber.i("Top Rated %s", movieDetails.toString())
            } catch (t: Throwable) {
                _status.value = MovieDBApiStatus.ERROR
                Timber.i(t)
            }

        }
    }


    private suspend fun getMovieDetails() = withContext(Dispatchers.IO) {
        Timber.i("Get Movie Details")
        TheMovieDBApi.retrofitService.getMovieDetails(_selectedMovie.value!!.id, API_KEY)
    }


    fun intentToPlayVideo(video: Video) {
        _intentToPlayVideo.value = video
    }

    fun intentToPlayVideoCompleted() {
        _intentToPlayVideo.value = null
    }

    fun intentToViewReview(review: Review) {
        _intentToViewReview.value = review
    }

    fun intentToViewReviewCompleted() {
        _intentToViewReview.value = null
    }

}