package com.example.mymovies.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mymovies.R
import com.example.mymovies.app.MoviesApplication
import com.example.mymovies.databinding.ActivityMainBinding
import com.example.mymovies.network.ApiInterface
import com.example.mymovies.network.response.Genre
import com.example.mymovies.shared.constants.EndPoints
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory:ViewModelFactory<MainViewModel>

    private lateinit var binding: ActivityMainBinding

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MoviesApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.mainBottomNavigationView
        val navController = findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navController)

        observeChanges()
    }
    private fun observeChanges(){
        viewModel.getViewState().observe(this) {
            when (it) {
                is MainViewModel.ViewState.ShowLoadingState -> handleProgressBar(it.loading)
                else -> {

                }
            }
        }
    }

    private fun handleProgressBar(loading: Boolean) {
        if(loading)
            binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }
}