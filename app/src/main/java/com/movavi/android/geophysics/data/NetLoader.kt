package com.movavi.android.geophysics.data

import com.movavi.android.geophysics.data.config.Config
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NetLoader {
    @GET("android/geo.json")
    fun getConfig(): Call<Config>
}