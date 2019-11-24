package com.movavi.android.geophysics.core

import com.movavi.android.geophysics.data.model.Hole
import java.math.RoundingMode
import java.text.DecimalFormat

class GetResUseCase {

    companion object {
        /**
         * Получить список расчётов корреляции и уравнений регрессии
         * [config] - объект принимаемый из сети, содержит в себе информацию о скважинах и её параметрах.
         * [ResItem] - объект содержащий в себе набор имён зависимых параметров, значение корреляции и уравнение регрессии
         */
        fun getResList(holes: ArrayList<Hole>): ArrayList<ResItem> {
            val df = DecimalFormat("#.###")
            df.roundingMode = RoundingMode.CEILING

            val resList = arrayListOf<ResItem>()
            for (i in 0 until holes[0].params.size) {
                if (!holes[0].params[i].isWell) continue
                for (j in 0 until holes[0].params.size) {
                    if (holes[0].params[j].isWell) continue
                    val listX = ArrayList<Double>()
                    val listY = ArrayList<Double>()
                    for (k in 0 until holes.size) {
                        listX.add(holes[k].params[i].variable)
                        listY.add(holes[k].params[j].variable)
                    }
                    resList.add(
                        ResItem(
                            "${holes[0].params[i].name} ${holes[0].params[j].name}",
                            MyMath.getPowCorellation(listX, listY),
                            MyMath.getRegression(listX, listY)
                        )
                    )

                }
            }
            return resList
        }
    }
}