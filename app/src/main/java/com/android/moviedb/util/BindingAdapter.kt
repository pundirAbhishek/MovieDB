package com.android.moviedb.util

import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.moviedb.R
import com.android.moviedb.network.*
import com.android.moviedb.ui.HomeMoviesAdapter
import com.android.moviedb.ui.MovieReviewsAdapter
import com.android.moviedb.ui.MovieVideosAdapter
import com.android.moviedb.ui.TvSeriesAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.lang.String

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
// TODO place holder and loading spinner
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
fun bindRecyclerView(recyclerView: RecyclerView, data: PagedResponse<Movie>?) {
    val adapter = recyclerView.adapter as HomeMoviesAdapter
    adapter.submitList(data?.results)
}

@BindingAdapter("listVideos")
fun bindVideosRecyclerView(recyclerView: RecyclerView, videos: VideosResponse?) {
    val adapter = recyclerView.adapter as MovieVideosAdapter
    adapter.submitList(videos?.videos)
}

@BindingAdapter("listReviews")
fun bindReviewsRecyclerView(recyclerView: RecyclerView, reviews: PagedResponse<Review>?) {
    val adapter = recyclerView.adapter as MovieReviewsAdapter
    adapter.submitList(reviews?.results)
}

@BindingAdapter("youtube_thumbnail")
fun youtubeThumbnail(imageView: ImageView, video: Video) {

    if (video.isYoutubeVideo()) {
        Glide.with(imageView.context)
            .load(String.format(YOUTUBE_THUMBNAIL, video.key))
            .apply(
                RequestOptions()
                    .placeholder(
                        ColorDrawable(
                            ContextCompat.getColor(
                                imageView.context,
                                R.color.colorAccent
                            )
                        )
                    )
                    .error(R.drawable.no_poster)
            )
            .into(imageView)


    }

}

@BindingAdapter("showList")
fun bindSeriesRecyclerView(recyclerView: RecyclerView, data: PagedResponse<TvSeries>?) {
    val adapter = recyclerView.adapter as TvSeriesAdapter
    adapter.submitList(data?.results)
}

// TODO place holder and loading spinner
@BindingAdapter("bindImage")
fun bindSeriesImage(imageView: ImageView, series: TvSeries) {
    val imgUrl = POSTER_BASE_URL + series.posterPath
    Glide.with(imageView.context)
        .load(imgUrl)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_img)
                .error(R.drawable.no_poster)
        )
        .into(imageView)
}