package com.movavi.android.geophysics.presentation.start

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.movavi.android.geophysics.R
import com.movavi.android.geophysics.databinding.ItemUrlBinding

class UrlListAdapter : RecyclerView.Adapter<UrlListAdapter.ViewHolder>() {

    private var urlList: ArrayList<UrlModel> = arrayListOf()

    fun addBaseUrl(url: UrlModel) {
        if (urlList.isEmpty()) {
            this.urlList.add(url)
            notifyDataSetChanged()
        }
    }

    fun addList(url: UrlModel) {
        this.urlList.add(url)
        notifyDataSetChanged()
    }

    fun getUrlList(): ArrayList<UrlModel> = urlList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_url,
            parent,
            false
        ) as ItemUrlBinding
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = urlList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.urlItem = urlList[position]
        holder.binding?.deleteButton?.setOnClickListener {
            urlList.remove(urlList[position])
            notifyDataSetChanged()
        }
    }

    class ViewHolder(binding: ItemUrlBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemUrlBinding? = binding
    }
}