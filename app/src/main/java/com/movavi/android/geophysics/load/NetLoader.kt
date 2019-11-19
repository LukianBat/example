package com.movavi.android.geophysics.load

import com.movavi.android.geophysics.load.config.Config
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NetLoader {
    @GET("android/geo.json")
    fun getConfig(): Call<Config>

    companion object Factory {
        fun create(): NetLoader {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://vincetti.ru/")
                .build()

            return retrofit.create(NetLoader::class.java);
        }
    }
}