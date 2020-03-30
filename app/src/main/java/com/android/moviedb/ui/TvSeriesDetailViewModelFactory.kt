package com.android.moviedb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.moviedb.network.TvSeries

class TvSeriesDetailViewModelFactory(private val series: TvSeries) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvSeriesDetailViewModel::class.java)) {
            return TvSeriesDetailViewModel(series) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}