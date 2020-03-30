package com.android.moviedb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.moviedb.databinding.ListItemVideoBinding
import com.android.moviedb.network.Video

class MovieVideosAdapter(private val onClickListener: OnClickListener) :
    androidx.recyclerview.widget.ListAdapter<Video, MovieVideosAdapter.MovieVideoViewHolder>(
        DiffCallback
    ) {

    companion object DiffCallback : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVideoViewHolder {
        val view = ListItemVideoBinding.inflate(LayoutInflater.from(parent.context))
        return MovieVideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieVideoViewHolder, position: Int) {
        val video = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(video)
        }
        holder.bind(video)
    }


    class MovieVideoViewHolder(private val binding: ListItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(video: Video) {
            binding.video = video

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }

    }

    class OnClickListener(private val onClickListener: (video: Video) -> Unit) {
        fun onClick(video: Video) = onClickListener(video)
    }
}




