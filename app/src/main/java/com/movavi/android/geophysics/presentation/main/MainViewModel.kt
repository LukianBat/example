package com.movavi.android.geophysics.presentation.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.movavi.android.geophysics.core.GetResUseCase
import com.movavi.android.geophysics.core.ResItem
import com.movavi.android.geophysics.data.ApiFactory
import com.movavi.android.geophysics.data.NetLoader
import com.movavi.android.geophysics.data.model.Config
import com.movavi.android.geophysics.data.model.Hole
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val urlList = MutableLiveData<ArrayList<String>>()

    private var _isCalculating = MutableLiveData<Boolean>()
    val isDownloadFinished: LiveData<Boolean>
        get() = _isCalculating

    private var _isReady = MutableLiveData<Boolean>()
    val isReady: LiveData<Boolean>
        get() = _isReady

    private var _listResult = MutableLiveData<List<ResItem>>()
    val listResult: LiveData<List<ResItem>>
        get() = _listResult

    private var _listData = MutableLiveData<ArrayList<Hole>>()
    val listData: LiveData<ArrayList<Hole>>
        get() = _listData

    private val loader: NetLoader

    init {
        _isCalculating.value = false
        _isReady.value = false
        loader = ApiFactory.createApi()
        urlList.observeForever {
            loadDataFromFile(it)
        }
    }

    fun loadingFinished(holes: ArrayList<Hole>) {
        _isCalculating.value = true
        _listData.value = holes
        calculatingFinished(GetResUseCase.getResList(Config(holes)))

    }

    fun calculatingFinished(resList: List<ResItem>) {
        _listResult.value = resList
        _isReady.value = true
    }

    @SuppressLint("CheckResult")
    private fun loadDataFromFile(urlList: ArrayList<String>) {
        val singles = urlList.map {
            loader.getConfig(it)
        }
        Single
            .concat(singles)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.i("TAG", it.holes.size.toString())
                loadingFinished(it.holes)
            }
    }
}