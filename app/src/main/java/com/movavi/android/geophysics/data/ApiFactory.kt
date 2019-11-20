package com.movavi.android.geophysics.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory {
    companion object Factory {

        private fun createRetrofit(): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://vincetti.ru/")
                .build()
        }

        fun createApi(): NetLoader {

            return createRetrofit().create(NetLoader::class.java)
        }
    }
}