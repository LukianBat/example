package com.movavi.android.geophysics.core

data class ResItem(val name: String, val corel: Float, val regres: String)

data class ResList(private val list: List<ResItem>)