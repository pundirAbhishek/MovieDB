package com.android.moviedb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.moviedb.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.popularMoviesList.apply {
            setHasFixedSize(true)
            adapter = HomeMoviesAdapter(HomeMoviesAdapter.OnClickListener {
                Toast.makeText(
                    context,
                    viewModel.popularMovies.value?.results?.size.toString(),
                    LENGTH_SHORT
                ).show()
            })
        }

        binding.topRatedMoviesList.apply {
            setHasFixedSize(true)
            adapter = HomeMoviesAdapter(HomeMoviesAdapter.OnClickListener {
                Toast.makeText(
                    context,
                    viewModel.topRatedMovies.value?.results?.size.toString(),
                    LENGTH_SHORT
                ).show()
            })
        }

        binding.upcomingMoviesList.apply {
            setHasFixedSize(true)
            adapter = HomeMoviesAdapter(HomeMoviesAdapter.OnClickListener {
                Toast.makeText(
                    context,
                    viewModel.upcomingMovies.value?.results?.size.toString(),
                    LENGTH_SHORT
                ).show()
            })
        }

        return binding.root

    }
}
