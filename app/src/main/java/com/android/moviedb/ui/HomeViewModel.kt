package com.android.moviedb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.moviedb.network.Movie
import com.android.moviedb.network.MovieDBApiStatus
import com.android.moviedb.network.PagedResponse
import com.android.moviedb.network.TheMovieDBApi
import com.android.moviedb.util.API_KEY
import com.android.moviedb.util.CATEGORY_POPULAR
import com.android.moviedb.util.CATEGORY_TOP_RATED
import com.android.moviedb.util.CATEGORY_UPCOMING
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class HomeViewModel : ViewModel() {

    // The internal MutableLiveData PopularMovie that stores the result of the request
    private val _popularMovies = MutableLiveData<PagedResponse<Movie>>()
    // The external immutable LiveData for the request PopularMovie
    val popularMovies: LiveData<PagedResponse<Movie>>
        get() = _popularMovies

    // The internal MutableLiveData TopRatedMovie that stores the result of the request
    private val _topRatedMovies = MutableLiveData<PagedResponse<Movie>>()
    // The external immutable LiveData for the request TopRatedMovies
    val topRatedMovies: LiveData<PagedResponse<Movie>>
        get() = _topRatedMovies

    // The internal MutableLiveData UpcomingMovie that stores the result of the request
    private val _upcomingMovies = MutableLiveData<PagedResponse<Movie>>()
    // The external immutable LiveData for the request UpcomingMovies
    val upcomingMovies: LiveData<PagedResponse<Movie>>
        get() = _upcomingMovies

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<MovieDBApiStatus>()
    // The external immutable LiveData for the request status String
    val status: LiveData<MovieDBApiStatus>
        get() = _status

    // navigate to the Detail Fragment
    private val _navigateToMovieDetail = MutableLiveData<Movie>()
    val navigateToMovieDetail: LiveData<Movie>
        get() = _navigateToMovieDetail

    init {
        launchPopularMoviesRequest()
        launchUpcomingMoviesRequest()
        launchTopRatedMoviesRequest()
    }

    private fun launchPopularMoviesRequest() {
        viewModelScope.launch {
            try {
                _status.value = MovieDBApiStatus.LOADING
                val movies = getPopularMovies()
                _status.value = MovieDBApiStatus.DONE
                _popularMovies.value = movies
                Timber.i("Popular %s", movies.results.size.toString())
            } catch (t: Throwable) {
                _status.value = MovieDBApiStatus.ERROR
                Timber.i(t)
            }
        }
    }

    private fun launchTopRatedMoviesRequest() {
        viewModelScope.launch {
            try {
                _status.value = MovieDBApiStatus.LOADING
                val movies = getTopRatedMovies()
                _status.value = MovieDBApiStatus.DONE
                _topRatedMovies.value = movies
                Timber.i("Top Rated %s", movies.results.size.toString())
            } catch (t: Throwable) {
                _status.value = MovieDBApiStatus.ERROR
                Timber.i(t)
            }
        }
    }

    private fun launchUpcomingMoviesRequest() {
        viewModelScope.launch {
            try {
                _status.value = MovieDBApiStatus.LOADING
                val movies = getUpcomingMovies()
                _status.value = MovieDBApiStatus.DONE
                _upcomingMovies.value = movies
                Timber.i("Upcoming %s", movies.results.size.toString())
            } catch (t: Throwable) {
                _status.value = MovieDBApiStatus.ERROR
                Timber.i(t)
            }
        }
    }

    private suspend fun getPopularMovies(page: Int = 1) =
        withContext(Dispatchers.IO) {
            Timber.i("Get Popular Movies")
            TheMovieDBApi.retrofitService.getMovies(
                CATEGORY_POPULAR,
                API_KEY,
                page
            )
        }

    private suspend fun getTopRatedMovies(page: Int = 1) =
        withContext(Dispatchers.IO) {
            Timber.i("Get Top Rated Movies")
            TheMovieDBApi.retrofitService.getMovies(
                CATEGORY_TOP_RATED,
                API_KEY,
                page
            )
        }

    private suspend fun getUpcomingMovies(page: Int = 1) =
        withContext(Dispatchers.IO) {
            Timber.i("Get Upcoming Movies")
            TheMovieDBApi.retrofitService.getMovies(
                CATEGORY_UPCOMING,
                API_KEY,
                page
            )
        }

    fun displayMovieDetail(movie: Movie) {
        _navigateToMovieDetail.value = movie
    }

    fun displayMovieDetailComplete() {
        _navigateToMovieDetail.value = null
    }
}