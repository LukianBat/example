package com.movavi.android.geophysics.core

import com.movavi.android.geophysics.data.model.Config

class GetResUseCase {

    companion object {
        /**
         * Получить список расчётов корреляции и уравнений регрессии
         * [config] - объект принимаемый из сети, содержит в себе информацию о скважинах и её параметрах.
         * [ResItem] - объект содержащий в себе набор имён зависимых параметров, значение корреляции и уравнение регрессии
         */
        fun getResList(config: Config): ArrayList<ResItem> {
            val resList = arrayListOf<ResItem>()
            for (i in 0 until config.holes[0].params.size) {
                if (!config.holes[0].params[i].isWell) continue
                for (j in 0 until config.holes[0].params.size) {
                    if (config.holes[0].params[j].isWell) continue
                    val listX = ArrayList<Double>()
                    val listY = ArrayList<Double>()
                    for (k in 0 until config.holes.size) {
                        listX.add(config.holes[k].params[i].variable)
                        listY.add(config.holes[k].params[j].variable)
                    }
                    resList.add(
                        ResItem(
                            "${config.holes[0].params[i].name} ${config.holes[0].params[j].name}",
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