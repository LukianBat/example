package com.movavi.android.geophysics.core

import com.movavi.android.geophysics.data.model.Hole
import java.math.RoundingMode
import java.text.DecimalFormat

class GetResUseCase {

    companion object {
        /**
         * Получить список расчётов корреляции и уравнений регрессии
         * [holes] - данные по скважинам
         * [ResItem] - объект содержащий в себе набор имён зависимых параметров, значение корреляции и уравнение регрессии
         */
        fun getFullResList(holes: ArrayList<Hole>): ArrayList<ArrayList<ResItem>> {
            val df = DecimalFormat("#.###")
            df.roundingMode = RoundingMode.CEILING

            val resList = ArrayList<ArrayList<ResItem>>()
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

                    val tmpList = ArrayList<ResItem>()
                    tmpList.add(
                            ResItem("${holes[0].params[i].name} ${holes[0].params[j].name}",
                            MyMath.getPowCorellation(listX, listY),
                            MyMath.getRegression(listX, listY))
                        )
                    resList.add(tmpList)

                }
            }
            return getPartialResList(holes, resList)
        }

        private fun getPartialResList(holes: ArrayList<Hole>, list: ArrayList<ArrayList<ResItem>>): ArrayList<ArrayList<ResItem>> {
            for (i in 0 until holes.size){
                val resList: List<ResItem> = getPartialResListSkip(holes, i)
                for(j in resList.indices){
                    list[j].add(resList[j])
                }
            }
            return list
        }


        /**
         * Получить список расчётов корреляции и уравнений регрессии, с исключением
         * [holes] - данные по скважинам
         * [skip] - какую строку пропускать
         */
        private fun getPartialResListSkip(holes: ArrayList<Hole>, skip: Int): List<ResItem> {
            val df = DecimalFormat("#.###")
            df.roundingMode = RoundingMode.CEILING

            val resList = ArrayList<ResItem>()
            for (i in 0 until holes[0].params.size) {
                if (!holes[0].params[i].isWell) continue
                for (j in 0 until holes[0].params.size) {
                    if (holes[0].params[j].isWell) continue
                    val listX = ArrayList<Double>()
                    val listY = ArrayList<Double>()
                    for (k in 0 until holes.size) {
                        // пропуск элементов
                        if (k != skip) {
                            listX.add(holes[k].params[i].variable)
                            listY.add(holes[k].params[j].variable)
                        }
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