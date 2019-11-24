package com.movavi.android.geophysics.presentation.calculating

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movavi.android.geophysics.core.GetResUseCase
import com.movavi.android.geophysics.core.ResItem
import com.movavi.android.geophysics.data.model.Hole

class CalculatingViewModel : ViewModel() {

    private var _isReady = MutableLiveData<Boolean>()
    val isReady: LiveData<Boolean>
        get() = _isReady

    private var _listResult = MutableLiveData<ArrayList<ResItem>>()
    val listResult: LiveData<ArrayList<ResItem>>
        get() = _listResult

    init {
        _isReady.value = false

    }

    fun calculate(list: ArrayList<Hole>) {
        calculatingFinished(GetResUseCase.getResList(list))
    }

    private fun calculatingFinished(resList: ArrayList<ResItem>) {
        _listResult.value = resList
        _isReady.value = true
    }

}