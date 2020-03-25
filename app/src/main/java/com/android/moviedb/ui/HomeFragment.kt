package com.android.moviedb.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.moviedb.databinding.FragmentHomeBinding
import com.android.moviedb.network.Movie
import com.android.moviedb.network.PopularMovie
import java.time.Duration

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

        binding.moviesGrid.apply {
            setHasFixedSize(true)
            adapter = HomeGridAdapter(HomeGridAdapter.OnClickListener {
                Toast.makeText(
                    context,
                    viewModel.popularMovies.value?.results?.size.toString(),
                    LENGTH_SHORT
                ).show()
            })
        }

        return binding.root

    }
}
