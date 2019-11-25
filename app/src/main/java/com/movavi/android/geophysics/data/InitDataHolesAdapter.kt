package com.movavi.android.geophysics.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.movavi.android.geophysics.R
import com.movavi.android.geophysics.data.model.Hole

class InitDataHolesAdapter : RecyclerView.Adapter<InitDataHolesAdapter.ViewHolder>() {
    private var listHoles = ArrayList<Hole>()
    private val viewPool = RecyclerView.RecycledViewPool()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.item_holes_rv_name)
        val recyclerView: RecyclerView = itemView.findViewById(R.id.item_holes_rv)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Create viewHolder etc
//        holder.innerRecyclerView.setRecycledViewPool(viewPool);
        //получаем все параметры для данной скважины
        val hole = listHoles[position]

        // TODO get string from resources
        holder.text.text = "${position + 1} hole"
        val childLayoutManager =
            LinearLayoutManager(holder.recyclerView.context, RecyclerView.HORIZONTAL, false)
        childLayoutManager.initialPrefetchItemCount = 5

        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            adapter = InitDataParamsAdapter(hole.params)
            setRecycledViewPool(viewPool)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_init_data_holes, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = listHoles.size

    fun setList(newList: ArrayList<Hole>) {
        listHoles = newList
        notifyDataSetChanged()
    }
}


