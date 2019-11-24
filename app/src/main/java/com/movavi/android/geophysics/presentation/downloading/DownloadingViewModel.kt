package com.movavi.android.geophysics.presentation.downloading

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movavi.android.geophysics.data.ApiFactory
import com.movavi.android.geophysics.data.NetLoader
import com.movavi.android.geophysics.data.model.Config
import com.movavi.android.geophysics.data.model.Hole
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DownloadingViewModel : ViewModel() {

    private var _listData = MutableLiveData<ArrayList<Hole>>()
    val listData: LiveData<ArrayList<Hole>>
        get() = _listData

    private var _netError = MutableLiveData<Boolean>()
    val netError: LiveData<Boolean>
        get() = _netError

    private val loader: NetLoader = ApiFactory.createApi()

    init {
        loadDataFromFile()
        _netError.value = false
    }

    fun loadingFinished(holes: ArrayList<Hole>) {
        _listData.value = holes

    }

    private fun loadDataFromFile() {
        loader.getConfig().enqueue(object : Callback<Config> {
            override fun onFailure(call: Call<Config>, t: Throwable) {
                Log.d("DEBUG", "error downloading")
                _netError.value = true
            }

            override fun onResponse(call: Call<Config>, response: Response<Config>) {
                response.body()?.let {
                    loadingFinished(it.holes)
                }
            }
        })
    }
}