package com.movavi.android.geophysics.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movavi.android.geophysics.load.NetLoader
import com.movavi.android.geophysics.load.config.Config
import com.movavi.android.geophysics.load.config.Hole
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(){

    private var _isCalculating = MutableLiveData<Boolean>()
    val isCalculating: LiveData<Boolean>
        get() = _isCalculating

    private val loader: NetLoader

    init {
        _isCalculating.value = false
        loader = NetLoader.create()
        loadDataFromFile()
    }

    fun loadingFinished(holes: ArrayList<Hole>) {
        for (hole in holes) {
            Log.d("DEBUG", "${hole.params}")
        }

        _isCalculating.value = true
    }

    fun loadDataFromFile() {
        loader.getConfig().enqueue(object : Callback<Config> {
            override fun onFailure(call: Call<Config>, t: Throwable) {
                Log.d("DEBUG", "error downloading")
            }

            override fun onResponse(call: Call<Config>, response: Response<Config>) {
                response.body()?.let {
                    loadingFinished(it.holes)
                }
            }
        })
    }


}