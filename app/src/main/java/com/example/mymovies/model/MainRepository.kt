package com.example.mymovies.model

import android.content.SharedPreferences
import android.util.Log
import com.example.mymovies.network.ApiInterface
import com.example.mymovies.network.response.Genre
import com.example.mymovies.network.response.MoviesByCategory
import com.example.mymovies.shared.storage.room_db.MoviesDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val prefs: SharedPreferences,
    private val room: MoviesDB
) {

    suspend fun getAllGenres(): List<Genre>? {
        var response: List<Genre>? = room.getMovieDao().retrieveMovieGenres()
        withContext(Dispatchers.IO) {
            if (response!!.isNotEmpty()) {
                Log.d("GENRES_FROM_CACHE", response.toString())
            } else {
                response = apiInterface.getAllAvailableGenres().genres
                room.getMovieDao().insertMovieGenres(response!!)
            }
        }
        return response
    }

    suspend fun getMoviesByCategory(genreId: String): MoviesByCategory? {
        var response: MoviesByCategory?
        withContext(Dispatchers.IO) {
//            if (response?.results?.isNotEmpty() == true) {
//                response?.results?.get(0)?.let { Log.d("CATEGORY_FROM_CACHE", it.toString()) }
//                response = room.getMovieDao().getAdventures()
//            } else {
                response = apiInterface.getMoviesByCategory(genreId)
                //room.getMovieDao().saveAdventureMovies(response!!)
                response?.results?.get(0)?.let { Log.d("CATEGORY_FROM_API", it.toString()) }
            //}
        }
        return response
    }

}