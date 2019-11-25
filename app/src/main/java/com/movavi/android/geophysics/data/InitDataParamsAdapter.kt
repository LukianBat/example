package com.movavi.android.geophysics.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.movavi.android.geophysics.R
import com.movavi.android.geophysics.data.model.Param
import java.text.DecimalFormat

class InitDataParamsAdapter(private val listParams : ArrayList<Param>) : RecyclerView.Adapter<InitDataParamsAdapter.ViewHolder>() {

    companion object{
        // округлятель
        private val df = DecimalFormat("#.###")
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.param_name)
        var type: TextView = itemView.findViewById(R.id.param_type)
        var valy: TextView = itemView.findViewById(R.id.param_val)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //получаем все параметры для данной скважины
        val child = listParams[position]
        holder.text.text = child.name
        holder.type.text = if (child.isWell) "+" else "-"
        holder.valy.text = df.format(child.variable).toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_init_data_params, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = listParams.size
}


