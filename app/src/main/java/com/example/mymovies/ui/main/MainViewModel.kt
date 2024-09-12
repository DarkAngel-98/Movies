package com.example.mymovies.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovies.model.MainRepository
import com.example.mymovies.network.response.Genre
import com.example.mymovies.network.response.MoviesByCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _viewState = MutableStateFlow(HomeViewState())
    val viewState: StateFlow<HomeViewState> = _viewState

    init {
        getAllMovieGenres()
    }

    private fun getAllMovieGenres() {
        viewModelScope.launch {
            _viewState.update {
                it.copy(showLoadingState = true)
            }
            val response = mainRepository.getAllGenres()
            if (response != null) {
                _viewState.update {
                    it.copy(
                        genres = response,
                        showLoadingState = false
                    )
                }
            }
        }
    }

    fun getMoviesByCategory(categoryId: String){
        viewModelScope.launch {
            _viewState.update { it.copy(showLoadingState = true) }
            val response = mainRepository.getMoviesByCategory(categoryId)
            if (response != null) {
                _viewState.update {
                    it.copy(
                        category = response,
                        showLoadingState = false
                    )
                }
            }
        }
    }

    data class HomeViewState(
        val showLoadingState: Boolean? = null,
        val genres: List<Genre>? = null,
        val category: MoviesByCategory? = null
    )

}