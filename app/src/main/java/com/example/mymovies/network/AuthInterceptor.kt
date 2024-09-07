package com.example.mymovies.network

import com.example.mymovies.shared.constants.EndPoints
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request?.newBuilder()
            ?.addHeader("Content-Type", EndPoints.CONTENT_TYPE)
            ?.addHeader("X-RapidAPI-Key", EndPoints.X_RAPID_API_KEY)
            ?.addHeader("X-RapidAPI-Host", EndPoints.X_RAPID_API_HOST)
            ?.build()!!
        return chain.proceed(request)
    }
}