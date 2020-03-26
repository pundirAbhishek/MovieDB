package com.android.moviedb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.moviedb.databinding.HomeListViewItemBinding
import com.android.moviedb.network.Movie

class HomeMoviesAdapter(private val onClickListener: OnClickListener) :
    androidx.recyclerview.widget.ListAdapter<Movie, HomeMoviesAdapter.MovieViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(HomeListViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(movie)
        }
        holder.bind(movie)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class MovieViewHolder(private val binding: HomeListViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    class OnClickListener(private val onClickListener : (movie: Movie) -> Unit){
        fun onClick(movie: Movie) = onClickListener(movie)
    }
}