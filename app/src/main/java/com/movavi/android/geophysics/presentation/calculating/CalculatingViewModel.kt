package com.movavi.android.geophysics.presentation.calculating

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movavi.android.geophysics.core.GetResUseCase
import com.movavi.android.geophysics.core.ResItem
import com.movavi.android.geophysics.data.model.Hole

const val SEC_TWO = 2000L
const val SEC_ONE = 1000L

class CalculatingViewModel : ViewModel() {

    private var _isReady = MutableLiveData<Boolean>()
    val isReady: LiveData<Boolean>
        get() = _isReady

    private var _listResult = MutableLiveData<ArrayList<ArrayList<ResItem>>>()
    val listResult: LiveData<ArrayList<ArrayList<ResItem>>>
        get() = _listResult

    private lateinit var timer: CountDownTimer

    init {
        _isReady.value = false
    }

    fun calculate(list: ArrayList<Hole>) {
        //for animation show (2 sec delay)
        timer = object : CountDownTimer(SEC_TWO, SEC_ONE) {
            override fun onFinish() {
                calculatingFinished(GetResUseCase.getFullResList(list))
            }

            override fun onTick(millisUntilFinished: Long) {
                //do nothing
            }
        }.start()
    }

    private fun calculatingFinished(resList: ArrayList<ArrayList<ResItem>>) {
        _listResult.value = resList
        _isReady.value = true
    }

}