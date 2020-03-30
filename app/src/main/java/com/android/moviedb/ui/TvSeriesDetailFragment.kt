package com.android.moviedb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.android.moviedb.databinding.FragmentTvSeriesDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class TvSeriesDetailFragment : Fragment() {

    private lateinit var binding: FragmentTvSeriesDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTvSeriesDetailBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this

        val series = TvSeriesDetailFragmentArgs.fromBundle(arguments!!).selectedSeries
        val viewModelFactory = TvSeriesDetailViewModelFactory(series)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(TvSeriesDetailViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.intentToPlayVideo.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                // TODO Send to the youtube app
                Toast.makeText(context, "Video", Toast.LENGTH_SHORT).show()
                viewModel.intentToPlayVideoCompleted()
            }
        })

        viewModel.intentToViewReview.observe(viewLifecycleOwner, Observer
        {
            if (it != null) {
                // TODO Send to TMDB to view the reviews
                Toast.makeText(context, "Review", Toast.LENGTH_SHORT).show()
                viewModel.intentToViewReviewCompleted()
            }
        })

        binding.seriesVideoList.apply {
            setHasFixedSize(true)
            adapter = VideosAdapter(VideosAdapter.OnClickListener {
                viewModel.intentToPlayVideo(it)
            })
        }

        binding.seriesReviewList.apply {
            setHasFixedSize(true)
            adapter = ReviewsAdapter(ReviewsAdapter.OnClickListener {
                viewModel.intentToViewReview(it)
            })
        }

        return binding.root
    }
}

