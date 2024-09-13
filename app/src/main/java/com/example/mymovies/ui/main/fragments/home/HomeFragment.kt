package com.example.mymovies.ui.main.fragments.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovies.app.MoviesApplication
import com.example.mymovies.databinding.FragmentHomeBinding
import com.example.mymovies.network.response.Genre
import com.example.mymovies.network.response.MoviesByCategory
import com.example.mymovies.shared.listeners.CategoryClick
import com.example.mymovies.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
@AndroidEntryPoint
class HomeFragment : Fragment(), CategoryClick {
    private lateinit var binding: FragmentHomeBinding

    private val viewModel: MainViewModel by viewModels()
    private val movieCategoriesAdapter by lazy { MovieCategoriesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvCategories.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            rvCategories.adapter = movieCategoriesAdapter
        }
        movieCategoriesAdapter.setListener(this@HomeFragment)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.viewState.collectLatest {
                    it.genres?.let { genres ->
                        movieCategoriesAdapter.submitList(genres)
                    }
                    it.category?.let { category ->
                        presentMovieByCategory(category)
                    }
                    Log.d("Collect", "Collecting")
                }
            }
        }
    }

    private fun presentMovieByCategory(category: MoviesByCategory?) {
        Toast.makeText(requireContext(), category?.results?.get(0)?.title, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onCategoryClick(genre: Genre) {

    }
}