package com.example.mymovies.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.mymovies.network.ApiInterface
import com.example.mymovies.network.AuthInterceptor
import com.example.mymovies.shared.constants.EndPoints
import com.example.mymovies.shared.constants.RoomConstants.DATABASE_NAME
import com.example.mymovies.shared.constants.SharedPreferencesConstants.MOVIES_SHARED_PREFERENCES
import com.example.mymovies.shared.storage.room_db.MoviesDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor(AuthInterceptor())

        return builder.build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(EndPoints.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext app: Context): SharedPreferences {
        return app.getSharedPreferences(
            MOVIES_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext app: Context): MoviesDB {
        return Room.databaseBuilder(app, MoviesDB::class.java, DATABASE_NAME).build()
    }
}