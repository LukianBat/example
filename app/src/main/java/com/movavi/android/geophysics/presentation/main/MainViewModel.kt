package com.movavi.android.geophysics.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movavi.android.geophysics.core.GetResUseCase
import com.movavi.android.geophysics.core.ResItem
import com.movavi.android.geophysics.data.ApiFactory
import com.movavi.android.geophysics.data.NetLoader
import com.movavi.android.geophysics.data.model.Config
import com.movavi.android.geophysics.data.model.Hole
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private var _isCalculating = MutableLiveData<Boolean>()
    val isDownloadFinished: LiveData<Boolean>
        get() = _isCalculating

    private var _isReady = MutableLiveData<Boolean>()
    val isReady: LiveData<Boolean>
        get() = _isReady

    private var _listResult = MutableLiveData<ArrayList<ArrayList<ResItem>>>()
    val listResult: LiveData<ArrayList<ArrayList<ResItem>>>
        get() = _listResult

    private var _listData = MutableLiveData<ArrayList<Hole>>()
    val listData: LiveData<ArrayList<Hole>>
        get() = _listData

    private val loader: NetLoader

    init {
        _isCalculating.value = false
        _isReady.value = false
        loader = ApiFactory.createApi()
        loadDataFromFile()
    }

    fun loadingFinished(holes: ArrayList<Hole>) {
        _isCalculating.value = true
        _listData.value = holes
        calculatingFinished(GetResUseCase.getFullResList(holes))

    }

    fun calculatingFinished(resList: ArrayList<ArrayList<ResItem>>) {
        _listResult.value = resList
        _isReady.value = true
    }

    private fun loadDataFromFile() {
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