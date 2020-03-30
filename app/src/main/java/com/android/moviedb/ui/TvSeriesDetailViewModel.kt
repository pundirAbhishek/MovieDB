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

class TvSeriesDetailViewModel(series: TvSeries) : ViewModel() {

    // The internal MutableLiveData For Selected Movie that stores the  detailed movie
    private val _selectedSeries = MutableLiveData<TvSeries>()

    // The external immutable LiveData for the Selected Movie
    val selectedSeries: LiveData<TvSeries>
        get() = _selectedSeries

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<MovieDBApiStatus>()

    // The external immutable LiveData for the request status String
    val status: LiveData<MovieDBApiStatus>
        get() = _status

    // The internal MutableLiveData MovieVideos that stores the result of the request
    private val _seriesVideos = MutableLiveData<VideosResponse>()

    // The external immutable LiveData for the request MovieVideos
    val seriesVideos: LiveData<VideosResponse>
        get() = _seriesVideos

    // The internal MutableLiveData MovieReviews that stores the result of the request
    private val _seriesReviews = MutableLiveData<PagedResponse<Review>>()

    // The external immutable LiveData for the request MovieReviews
    val seriesReviews: LiveData<PagedResponse<Review>>
        get() = _seriesReviews

    // Play Video in Youtube
    private val _intentToPlayVideo = MutableLiveData<Video>()
    val intentToPlayVideo: LiveData<Video>
        get() = _intentToPlayVideo

    // View Review through url
    private val _intentToViewReview = MutableLiveData<Review>()
    val intentToViewReview: LiveData<Review>
        get() = _intentToViewReview

    // The internal MutableLiveData MovieDetails that stores the result of the request
    private val _seriesDetails = MutableLiveData<TvSeriesDetailsResponse>()

    // The external immutable LiveData for the request MovieDetails
    val seriesDetails: LiveData<TvSeriesDetailsResponse>
        get() = _seriesDetails

    // The internal MutableLiveData MovieDetails that stores the result of the request
    private val _seriesCast = MutableLiveData<CreditsResponse<Cast>>()

    // The external immutable LiveData for the request MovieDetails
    val seriesCast: LiveData<CreditsResponse<Cast>>
        get() = _seriesCast

    // The internal MutableLiveData MovieDetails that stores the result of the request
    private val _similarSeries = MutableLiveData<PagedResponse<TvSeries>>()

    // The external immutable LiveData for the request MovieDetails
    val similarSeries: LiveData<PagedResponse<TvSeries>>
        get() = _similarSeries


    init {
        _selectedSeries.value = series
//        launchMovieVideosRequest()
//        launchMovieReviewsRequest()

        launchMovieDetailsRequest()
    }

    private fun launchMovieDetailsRequest() {
        viewModelScope.launch {
            try {
                _status.value = MovieDBApiStatus.LOADING
                val seriesDetails = getSeriesDetails()
                _status.value = MovieDBApiStatus.DONE


                _seriesDetails.value = seriesDetails
                _seriesReviews.value = _seriesDetails.value!!.reviews
                _seriesVideos.value = _seriesDetails.value!!.videos
                _seriesCast.value = _seriesDetails.value!!.credits
                _similarSeries.value = _seriesDetails.value!!.similar
                Timber.i("Top Rated %s", seriesDetails.toString())
            } catch (t: Throwable) {
                _status.value = MovieDBApiStatus.ERROR
                Timber.i(t)
            }

        }
    }

    private suspend fun getSeriesDetails() = withContext(Dispatchers.IO) {
        Timber.i("Get Series Details")
        TheMovieDBApi.retrofitService.getSeriesDetails(_selectedSeries.value!!.id, API_KEY)
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