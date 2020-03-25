package com.android.moviedb

import android.app.Application
import timber.log.Timber

class MovieDBApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}