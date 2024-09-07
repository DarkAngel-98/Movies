package com.example.mymovies.ui.main

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelFactory<T : ViewModel?> @Inject constructor(
    private val viewModel: dagger.Lazy<T>
    ): ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    @NonNull
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel.get() as T
    }
}