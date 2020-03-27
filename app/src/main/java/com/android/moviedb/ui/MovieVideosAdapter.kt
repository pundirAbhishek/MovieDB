package com.android.moviedb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.moviedb.databinding.ListItemMovieVideoBinding
import com.android.moviedb.network.MovieVideo
import com.android.moviedb.util.SITE_YOUTUBE
import java.lang.String
import java.util.*

class MovieVideosAdapter(private val onClickListener: OnClickListener) :
    androidx.recyclerview.widget.ListAdapter<MovieVideo, MovieVideosAdapter.MovieVideoViewHolder>(
        DiffCallback
    ) {

    companion object DiffCallback : DiffUtil.ItemCallback<MovieVideo>() {
        override fun areItemsTheSame(oldItem: MovieVideo, newItem: MovieVideo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MovieVideo, newItem: MovieVideo): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVideoViewHolder {
        val view = ListItemMovieVideoBinding.inflate(LayoutInflater.from(parent.context))
        return MovieVideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieVideoViewHolder, position: Int) {
        val video = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(video)
        }
        holder.bind(video)
    }


    class MovieVideoViewHolder(private val binding: ListItemMovieVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(video: MovieVideo) {
            binding.video = video

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }

    }

    class OnClickListener(private val onClickListener: (video: MovieVideo) -> Unit) {
        fun onClick(video: MovieVideo) = onClickListener(video)
    }
}



