package com.example.mymovies.shared.listeners

import com.example.mymovies.network.response.Genre

interface CategoryClick {
    fun onCategoryClick(genre: Genre)
}