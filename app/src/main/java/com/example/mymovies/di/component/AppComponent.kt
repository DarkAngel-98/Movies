package com.example.mymovies.di.component

import com.example.mymovies.ui.main.MainActivity
import com.example.mymovies.app.MoviesApplication
import com.example.mymovies.di.modules.AppModule
import com.example.mymovies.ui.main.fragments.favorites.FavoritesFragment
import com.example.mymovies.ui.main.fragments.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(moviesApplication: MoviesApplication)
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(favoritesFragment: FavoritesFragment)
}