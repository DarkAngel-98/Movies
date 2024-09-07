package com.example.mymovies.network.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mymovies.shared.constants.RoomConstants.MOVIE_TABLE_NAME

@Entity
data class Genre(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
){
    var isClicked: Boolean = false
}