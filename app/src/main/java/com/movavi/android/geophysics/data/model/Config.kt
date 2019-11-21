package com.movavi.android.geophysics.data.model

data class Config(val holes: ArrayList<Hole>)

data class Hole(val params: ArrayList<Param>)

data class Param(
    val name: String,
    val isWell: Boolean,
    val variable: Float
)