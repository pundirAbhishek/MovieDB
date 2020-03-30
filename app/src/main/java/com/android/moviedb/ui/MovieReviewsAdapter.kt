package com.android.moviedb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.moviedb.databinding.ListItemReviewBinding
import com.android.moviedb.network.Review

class MovieReviewsAdapter(val onClickListener: OnClickListener) :
    androidx.recyclerview.widget.ListAdapter<Review, MovieReviewsAdapter.MovieReviewViewHolder>(
        DiffCallback
    ) {

    companion object DiffCallback : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder {
        val view = ListItemReviewBinding.inflate(LayoutInflater.from(parent.context))
        return MovieReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        val review = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(review)
        }
        holder.bind(review)
    }

    class MovieReviewViewHolder(private val binding: ListItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review) {
            binding.review = review

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val onClickListener: (review: Review) -> Unit) {
        fun onClick(review: Review) = onClickListener(review)
    }
}