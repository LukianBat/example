package com.movavi.android.geophysics.data

import com.movavi.android.geophysics.data.model.Config
import retrofit2.Call
import retrofit2.http.GET

interface NetLoader {
    @GET("android/geo.json")
    fun getConfig(): Call<Config>
}