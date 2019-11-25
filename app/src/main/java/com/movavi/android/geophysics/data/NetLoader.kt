package com.movavi.android.geophysics.data

import com.movavi.android.geophysics.data.model.Config
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface NetLoader {

    @GET
    fun getConfig(@Url url: String): Single<Config>

}