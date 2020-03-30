package com.android.moviedb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.android.moviedb.databinding.FragmentTvSeriesBinding

/**
 * A simple [Fragment] subclass.
 */
class TvSeriesFragment : Fragment() {

    private lateinit var binding: FragmentTvSeriesBinding
    private val viewModel: TvSeriesViewModel by lazy {
        ViewModelProvider(this).get(TvSeriesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTvSeriesBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        binding.popularSeriesList.apply {
            setHasFixedSize(true)
            adapter = TvSeriesAdapter(TvSeriesAdapter.OnClickListener {
                viewModel.displaySeriesDetail(it)
            })
        }


        binding.topRatedSeriesList.apply {
            setHasFixedSize(true)
            adapter = TvSeriesAdapter(TvSeriesAdapter.OnClickListener {
                viewModel.displaySeriesDetail(it)
            })
        }

        binding.onAirSeriesList.apply {
            setHasFixedSize(true)
            adapter = TvSeriesAdapter(TvSeriesAdapter.OnClickListener {
                viewModel.displaySeriesDetail(it)
            })
        }

        viewModel.navigateToTvSeriesDetail.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController()
                    .navigate(
                        TvSeriesFragmentDirections.actionDrawerItemTvToTvSeriesDetailFragment(
                            it
                        )
                    )
                viewModel.displaySeriesDetailComplete()
            }
        })


        return binding.root

    }

}
