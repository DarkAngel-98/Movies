package com.example.mymovies.ui.main.fragments.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mymovies.R
import com.example.mymovies.app.MoviesApplication
import com.example.mymovies.databinding.FragmentFavoritesBinding
import com.example.mymovies.ui.main.MainViewModel
import com.example.mymovies.ui.main.ViewModelFactory
import javax.inject.Inject

class FavoritesFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>

    private lateinit var binding: FragmentFavoritesBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]
    }

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}