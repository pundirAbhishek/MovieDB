package com.android.moviedb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.android.moviedb.databinding.FragmentDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this

        val movie = DetailFragmentArgs.fromBundle(arguments!!).selectedMovie
        val viewModelFactory = DetailViewModelFactory(movie)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.intentToPlayVideo.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                // TODO Send to the youtube app
                Toast.makeText(context, "Video", LENGTH_SHORT).show()
                viewModel.intentToPlayVideoCompleted()
            }
        })

        viewModel.intentToViewReview.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                // TODO Send to TMDB to view the reviews
                Toast.makeText(context, "Review", LENGTH_SHORT).show()
                viewModel.intentToViewReviewCompleted()
            }
        })

        binding.movieVideos.apply {
            setHasFixedSize(true)
            adapter = VideosAdapter(VideosAdapter.OnClickListener {
                viewModel.intentToPlayVideo(it)
            })
        }

        binding.movieReviews.apply {
            adapter = ReviewsAdapter(ReviewsAdapter.OnClickListener {
                viewModel.intentToViewReview(it)
            })
        }

        return binding.root

    }

}
