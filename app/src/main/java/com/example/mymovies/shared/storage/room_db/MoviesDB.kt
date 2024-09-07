package com.example.mymovies.shared.storage.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mymovies.network.response.Genre

@Database(entities = [Genre::class], version = 1, exportSchema = true)
abstract class MoviesDB: RoomDatabase() {
    abstract fun getMovieDao(): MovieDao

}