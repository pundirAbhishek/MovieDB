package com.android.moviedb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.moviedb.databinding.TvSeriesListViewItemBinding
import com.android.moviedb.network.TvSeries

class TvSeriesAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<TvSeries, TvSeriesAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TvSeriesListViewItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val series = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(series)
        }
        holder.bind(series)
    }

    class ViewHolder(private val binding: TvSeriesListViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(series: TvSeries) {
            binding.series = series

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<TvSeries>() {
        override fun areItemsTheSame(oldItem: TvSeries, newItem: TvSeries): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TvSeries, newItem: TvSeries): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(private val onClickListener: (series: TvSeries) -> Unit) {
        fun onClick(series: TvSeries) = onClickListener(series)
    }
}


