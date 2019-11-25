package com.movavi.android.geophysics.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    /**
     * Объект для обмена результатов подсчёта между фрагментами
     * [ResItem] - объект содержащий в себе набор имён зависимых параметров, значение корреляции и уравнение регрессии.
     */
    val results = MutableLiveData<ArrayList<ArrayList<ResItem>>>()
}