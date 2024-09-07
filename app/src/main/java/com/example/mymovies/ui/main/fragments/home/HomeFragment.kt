package com.example.mymovies.ui.main.fragments.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovies.app.MoviesApplication
import com.example.mymovies.databinding.FragmentHomeBinding
import com.example.mymovies.network.response.Genre
import com.example.mymovies.network.response.MoviesByCategory
import com.example.mymovies.ui.main.MainViewModel
import com.example.mymovies.ui.main.ViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]
    }
    private val movieCategoriesAdapter by lazy { MovieCategoriesAdapter() }
    private val TAG: String = "HOME_FRAGMENT_TAG"
    private var movieCategories= mutableListOf<Genre>()

    override fun onAttach(context: Context) {
        MoviesApplication.appComponent.inject(this)
        super.onAttach(context)
        arguments?.let {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

        viewModel.getAllMovieGenres()
        //viewModel.getMoviesByCategory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observeChanges()
        setListeners()
    }

    private fun initUi() {
        binding.apply {
            rvCategories.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL, false)
            rvCategories.adapter = movieCategoriesAdapter
            rvCategories.setHasFixedSize(true)
        }
    }
    private fun setListeners(){
//        movieCategoriesAdapter.setListener
    }

    private fun observeChanges() {
        viewModel.getViewState().observe(requireActivity()) {
            when (it) {
                is MainViewModel.ViewState.ShowMovieGenres -> presentMovieGenres(it.genres)
                is MainViewModel.ViewState.ShowMovieByCategory -> presentMovieByCategory(it.category)
                else -> {

                }
            }
        }
    }

    private fun presentMovieByCategory(category: MoviesByCategory?) {
        Toast.makeText(requireContext(), category?.results?.get(0)?.title, Toast.LENGTH_SHORT).show()
    }

    private fun presentMovieGenres(genres: List<Genre>) {
        Log.d(TAG, genres.toString())
        movieCategories.clear()
        movieCategories.addAll(genres)
        movieCategoriesAdapter.setData(genres)
    }
}