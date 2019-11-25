package com.movavi.android.geophysics.presentation.downloading

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movavi.android.geophysics.data.ApiFactory
import com.movavi.android.geophysics.data.NetLoader
import com.movavi.android.geophysics.data.model.Config
import com.movavi.android.geophysics.data.model.Hole
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.zipWith
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.sin

class DownloadingViewModel : ViewModel() {

    val urlList = MutableLiveData<ArrayList<String>>()

    private var _listData = MutableLiveData<ArrayList<Hole>>()
    val listData: LiveData<ArrayList<Hole>>
        get() = _listData

    private var _netError = MutableLiveData<Boolean>()
    val netError: LiveData<Boolean>
        get() = _netError

    private val loader: NetLoader = ApiFactory.createApi()

    init {
        _netError.value = false
        urlList.observeForever {
            loadDataFromFile(it)
        }

    }

    @SuppressLint("CheckResult")
    private fun loadDataFromFile(urlList: ArrayList<String>) {
        val singles = urlList.map {
            loader.getConfig(it)
        }
        val holes = arrayListOf<Hole>()
        Single
            .concat(singles)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                {
                    Log.i("TAG", it.holes.size.toString())
                    holes.addAll(it.holes)
                },
                {

                },
                {
                    loadingFinished(holes)
                }

            )

    }

    fun loadingFinished(holes: ArrayList<Hole>) {
        _listData.value = holes
    }
}