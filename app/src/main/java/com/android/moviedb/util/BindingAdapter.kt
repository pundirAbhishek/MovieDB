package com.android.moviedb.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.moviedb.R
import com.android.moviedb.network.Movie
import com.android.moviedb.network.PopularMovie
import com.android.moviedb.ui.HomeGridAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, movie: Movie) {
    val imgUrl = POSTER_BASE_URL + movie.posterPath
    Glide.with(imageView.context)
        .load(imgUrl)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_img)
                .error(R.drawable.no_poster)
        )
        .into(imageView)
}

/**
 * When there is no Movie data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: PopularMovie<Movie>?) {
    val adapter = recyclerView.adapter as HomeGridAdapter
    adapter.submitList(data?.results)
}
