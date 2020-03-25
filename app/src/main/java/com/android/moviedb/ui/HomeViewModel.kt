package com.android.moviedb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.moviedb.BuildConfig
import com.android.moviedb.network.Movie
import com.android.moviedb.network.MovieDBApiStatus
import com.android.moviedb.network.PopularMovie
import com.android.moviedb.network.TheMovieDBApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class HomeViewModel : ViewModel() {

    // The internal MutableLiveData PopularMovie that stores the result of the request
    private val _popularMovies = MutableLiveData<PopularMovie<Movie>>()
    // The external immutable LiveData for the request PopularMovie
    val popularMovies: LiveData<PopularMovie<Movie>>
        get() = _popularMovies

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<MovieDBApiStatus>()
    // The external immutable LiveData for the request status String
    val status: LiveData<MovieDBApiStatus>
        get() = _status

    init {
        viewModelScope.launch {
            try {
                _status.value = MovieDBApiStatus.LOADING
                val movies = getPopularMovies()
                _status.value = MovieDBApiStatus.DONE
                _popularMovies.value = movies
            } catch (t: Throwable) {
                _status.value = MovieDBApiStatus.ERROR
                Timber.i(t)
            }
        }
    }

    private suspend fun getPopularMovies() =
        withContext(Dispatchers.IO) {
            Timber.i("Get Popular Movies")
            TheMovieDBApi.retrofitService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_KEY)
        }
}