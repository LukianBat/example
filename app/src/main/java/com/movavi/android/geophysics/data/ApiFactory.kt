package com.movavi.android.geophysics.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory {
    companion object Factory {

        private const val BASE_URL = "https://vincetti.ru/"

        private fun createRetrofit(): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }

        /**
         * Возвращает сгенерированный объект [NetLoader] для работы с сетью
         */
        fun createApi(): NetLoader {

            return createRetrofit().create(NetLoader::class.java)
        }
    }
}