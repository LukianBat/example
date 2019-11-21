package com.movavi.android.geophysics.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val results = MutableLiveData<ArrayList<ResItem>>()
}