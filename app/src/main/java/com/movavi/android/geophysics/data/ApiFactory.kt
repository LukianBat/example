package com.movavi.android.geophysics.data

import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory {
    companion object Factory {

        private const val BASE_URL = "https://localhost/"

        private fun createRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
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