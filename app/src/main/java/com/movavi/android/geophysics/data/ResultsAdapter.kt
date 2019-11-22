package com.movavi.android.geophysics.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.movavi.android.geophysics.R
import com.movavi.android.geophysics.core.ResItem

class ResultsAdapter : RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {

    var listResults = ArrayList<ArrayList<ResItem>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = listResults.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = listResults[position][0].name
        holder.corel.text = listResults[position][0].corel.toString()
        holder.regress.text = listResults[position][0].regres

        //TODO String to values!
        var tmpString = ""
        for (i in 1 until listResults[0].size){
            tmpString += "-${i}: Corel: ${listResults[position][i].corel}\nRegression: ${listResults[position][i].regres}\n"
        }
        holder.partial.text = tmpString
    }

    /**
     * Принимает на вход список [ResItem], приравнивая [listResults] полученный список
     * [ResItem] - объект содержащий в себе набор имён зависимых параметров, значение корреляции и уравнение регрессии.
     */
    fun setList(newList: ArrayList<ArrayList<ResItem>>) {
        listResults = newList
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.item_name)
        var corel: TextView = itemView.findViewById(R.id.item_corel_name_value)
        var regress: TextView = itemView.findViewById(R.id.item_regress_name_value)
        var partial: TextView = itemView.findViewById(R.id.item_data_partial)

    }
}