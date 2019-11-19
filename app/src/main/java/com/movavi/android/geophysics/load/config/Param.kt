package com.movavi.android.geophysics.load.config

class Param(
    val name: String,
    val isWell: Boolean,
    val variable: Float
){
    override fun toString(): String {
        return "name: $name ${if(isWell) "+" else "-"} var $variable"
    }
}