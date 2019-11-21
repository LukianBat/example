package com.movavi.android.geophysics.presentation.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel : ViewModel(){

    private val _result: MutableLiveData<List<String>> = MutableLiveData()
    val result: LiveData<List<String>>
        get() = _result


}