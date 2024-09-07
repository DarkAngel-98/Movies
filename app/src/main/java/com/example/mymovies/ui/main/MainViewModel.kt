package com.example.mymovies.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovies.model.MainRepository
import com.example.mymovies.network.response.Genre
import com.example.mymovies.network.response.MoviesByCategory
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val viewState: MutableLiveData<ViewState> = MutableLiveData()
    fun getViewState(): LiveData<ViewState> = viewState

    fun getAllMovieGenres() {
        viewModelScope.launch {
            viewState.value = ViewState.ShowLoadingState(true)
            val response = mainRepository.getAllGenres()
            if (response != null) {
                viewState.value = ViewState.ShowMovieGenres(response)
                viewState.value = ViewState.ShowLoadingState(false)
            }
        }
    }

    fun getMoviesByCategory(categoryId: String){
        viewModelScope.launch {
            viewState.value = ViewState.ShowLoadingState(true)
            val response = mainRepository.getMoviesByCategory(categoryId)
            if(response != null){
                viewState.value = ViewState.ShowMovieByCategory(response)
                viewState.value = ViewState.ShowLoadingState(false)
            }
        }
    }

    sealed class ViewState {
        data class ShowLoadingState(val loading: Boolean) : ViewState()
        data class ShowMovieGenres(val genres: List<Genre>) : ViewState()
        data class ShowMovieByCategory(val category: MoviesByCategory?) : ViewState()
    }

}