package com.android.moviedb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.moviedb.databinding.ListItemMovieReviewBinding
import com.android.moviedb.network.MovieReview

class MovieReviewsAdapter(val onClickListener: OnClickListener) :
    androidx.recyclerview.widget.ListAdapter<MovieReview, MovieReviewsAdapter.MovieReviewViewHolder>(
        DiffCallback
    ) {

    companion object DiffCallback : DiffUtil.ItemCallback<MovieReview>() {
        override fun areItemsTheSame(oldItem: MovieReview, newItem: MovieReview): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MovieReview, newItem: MovieReview): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder {
        val view = ListItemMovieReviewBinding.inflate(LayoutInflater.from(parent.context))
        return MovieReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        val review = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(review)
        }
        holder.bind(review)
    }

    class MovieReviewViewHolder(private val binding: ListItemMovieReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(review: MovieReview) {
            binding.review = review

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val onClickListener: (review: MovieReview) -> Unit) {
        fun onClick(review: MovieReview) = onClickListener(review)
    }
}