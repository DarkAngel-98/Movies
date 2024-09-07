package com.example.mymovies.network

import com.example.mymovies.network.response.Genres
import com.example.mymovies.network.response.MoviesByCategory
import com.example.mymovies.shared.constants.EndPoints
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(EndPoints.GET_ALL_GENRES)
    suspend fun getAllAvailableGenres(): Genres

    @GET(EndPoints.SEARCH_BY_GENRE)
    suspend fun getMoviesByCategory(@Query("with_genres") category: String?): MoviesByCategory?
}