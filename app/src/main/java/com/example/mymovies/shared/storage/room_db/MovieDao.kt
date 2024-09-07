package com.example.mymovies.shared.storage.room_db


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mymovies.network.response.Genre
import com.example.mymovies.network.response.MoviesByCategory

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovieGenres(genres: List<Genre>)

    @Query("SELECT * FROM Genre")
    suspend fun retrieveMovieGenres(): List<Genre>

//    @Insert
//    suspend fun saveAdventureMovies(adventures: MoviesByCategory?)
//
//    @Query("SELECT * FROM Result")
//    suspend fun getAdventures(): MoviesByCategory?

//    @Update()
//    suspend fun updateMovieTable()

}