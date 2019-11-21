package com.movavi.android.geophysics.data

import com.movavi.android.geophysics.data.model.Config
import retrofit2.Call
import retrofit2.http.GET

interface NetLoader {
    @GET(SUB_URL)
    fun getConfig(): Call<Config>

    companion object {
        private const val SUB_URL = "android/geo.json"
    }
}