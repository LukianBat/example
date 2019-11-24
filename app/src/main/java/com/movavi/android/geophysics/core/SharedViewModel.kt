package com.movavi.android.geophysics.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movavi.android.geophysics.data.model.Hole

class SharedViewModel : ViewModel() {
    /**
     * Объект для обмена результатов подсчёта между фрагментами
     * [ResItem] - объект содержащий в себе набор имён зависимых параметров, значение корреляции и уравнение регрессии.
     */
    val results = MutableLiveData<ArrayList<ResItem>>()
    val initialData = MutableLiveData<ArrayList<Hole>>()
}