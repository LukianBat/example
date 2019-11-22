package com.movavi.android.geophysics.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.movavi.android.geophysics.R
import com.movavi.android.geophysics.data.model.Hole

class DataAdapter : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    private var listHoles = ArrayList<Hole>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.paramTextView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //получаем все параметры для данной скважины
        var string = ""

        listHoles[position].params.forEach {
            val name = it.name
            val variable = it.variable
            val type = it.isWell

            string += "$name ${if (type) "сквж" else "сейсм"} $variable \n"
        }

        holder.text.text = string
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = listHoles.size

    fun setList(newList: ArrayList<Hole>) {
        listHoles = newList
        notifyDataSetChanged()
    }
}


