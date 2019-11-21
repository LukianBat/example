package com.movavi.android.geophysics.presentation.main

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movavi.android.geophysics.core.ResItem
import com.movavi.android.geophysics.data.ApiFactory
import com.movavi.android.geophysics.data.NetLoader
import com.movavi.android.geophysics.data.config.Config
import com.movavi.android.geophysics.data.config.Hole
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

    private var _listResult = MutableLiveData<List<ResItem>>()
    val listResult: LiveData<List<ResItem>>
        get() = _listResult

    private val loader: NetLoader
    lateinit var timer: CountDownTimer

    init {
        _isCalculating.value = false
        _isReady.value = false
        loader = ApiFactory.createApi()
        loadDataFromFile()
    }

    fun loadingFinished(holes: ArrayList<Hole>) {
        for (hole in holes) {
            Log.d("DEBUG", "${hole.params}")
        }

        _isCalculating.value = true

        // TODO убрать таймер
        timer = object : CountDownTimer(4000, 1000) {

            override fun onFinish() {
                // завершение подсчета
                calculatingFinished(ArrayList<ResItem>())
            }

            override fun onTick(millisUntilFinished: Long) {
                // do nothing
            }
        }.start()
    }

    fun calculatingFinished(resList: List<ResItem>) {
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

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}