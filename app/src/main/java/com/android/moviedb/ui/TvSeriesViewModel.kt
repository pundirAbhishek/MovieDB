package com.android.moviedb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.moviedb.network.*
import com.android.moviedb.util.API_KEY
import com.android.moviedb.util.CATEGORY_POPULAR
import com.android.moviedb.util.CATEGORY_TOP_RATED
import com.android.moviedb.util.CATEGORY_UPCOMING
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class TvSeriesViewModel : ViewModel() {

    // The internal MutableLiveData PopularSeries that stores the result of the request
    private val _popularSeries = MutableLiveData<PagedResponse<TvSeries>>()
    // The external immutable LiveData for the request PopularSeries
    val popularSeries: LiveData<PagedResponse<TvSeries>>
        get() = _popularSeries

    // The internal MutableLiveData OnAirSeries that stores the result of the request
    private val _onAirSeries = MutableLiveData<PagedResponse<TvSeries>>()
    // The external immutable LiveData for the request OnAirSeries
    val onAirSeries: LiveData<PagedResponse<TvSeries>>
        get() = _onAirSeries

    // The internal MutableLiveData TopRatedSeries that stores the result of the request
    private val _topRatedSeries = MutableLiveData<PagedResponse<TvSeries>>()
    // The external immutable LiveData for the request TopRatedSeries
    val topRatedSeries: LiveData<PagedResponse<TvSeries>>
        get() = _topRatedSeries

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<MovieDBApiStatus>()
    // The external immutable LiveData for the request status String
    val status: LiveData<MovieDBApiStatus>
        get() = _status

    // navigate to the TV Series Detail Fragment
    private val _navigateToTvSeriesDetail = MutableLiveData<TvSeries>()
    val navigateToTvSeriesDetail: LiveData<TvSeries>
        get() = _navigateToTvSeriesDetail

    init {
        launchPopularSeriesRequest()
        launchOnAirSeriesRequest()
        launchTopRatedSeriesRequest()
    }

    private fun launchPopularSeriesRequest() {
        viewModelScope.launch {
            try {
                _status.value = MovieDBApiStatus.LOADING
                val series = getPopularSeries()
                _status.value = MovieDBApiStatus.DONE
                _popularSeries.value = series
                Timber.i("Popular %s", series.results.size.toString())
            } catch (t: Throwable) {
                _status.value = MovieDBApiStatus.ERROR
                Timber.i(t)
            }
        }
    }

    private fun launchOnAirSeriesRequest() {
        viewModelScope.launch {
            try {
                _status.value = MovieDBApiStatus.LOADING
                val series = getOnAirSeries()
                _status.value = MovieDBApiStatus.DONE
                _onAirSeries.value = series
                Timber.i("Popular %s", series.results.size.toString())
            } catch (t: Throwable) {
                _status.value = MovieDBApiStatus.ERROR
                Timber.i(t)
            }
        }    }

    private fun launchTopRatedSeriesRequest() {
        viewModelScope.launch {
            try {
                _status.value = MovieDBApiStatus.LOADING
                val series = getTopRatedSeries()
                _status.value = MovieDBApiStatus.DONE
                _topRatedSeries.value = series
                Timber.i("Popular %s", series.results.size.toString())
            } catch (t: Throwable) {
                _status.value = MovieDBApiStatus.ERROR
                Timber.i(t)
            }
        }    }


    private suspend fun getPopularSeries(page: Int = 1) =
        withContext(Dispatchers.IO) {
            Timber.i("Get Popular Series")
            TheMovieDBApi.retrofitService.getPopularSeries(
                API_KEY,
                page
            )
        }

    private suspend fun getTopRatedSeries(page: Int = 1) =
        withContext(Dispatchers.IO) {
            Timber.i("Get Top Rated Series")
            TheMovieDBApi.retrofitService.getTopRatedSeries(
                API_KEY,
                page
            )
        }

    private suspend fun getOnAirSeries(page: Int = 1) =
        withContext(Dispatchers.IO) {
            Timber.i("Get OnAir Series")
            TheMovieDBApi.retrofitService.getOnAirSeries(
                API_KEY,
                page
            )
        }

    fun displaySeriesDetail(series: TvSeries) {
        _navigateToTvSeriesDetail.value = series
    }

    fun displaySeriesDetailComplete() {
        _navigateToTvSeriesDetail.value = null
    }
}