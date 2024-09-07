package com.example.mymovies.app

import android.app.Application
import com.example.mymovies.di.component.AppComponent
import com.example.mymovies.di.component.DaggerAppComponent
import com.example.mymovies.di.modules.AppModule

class MoviesApplication: Application() {

    companion object{
        lateinit var appComponent: AppComponent
    }


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.inject(this)
    }
}