package com.movavi.android.geophysics.presentation.start


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

import com.movavi.android.geophysics.R
import com.movavi.android.geophysics.core.SharedViewModel
import com.movavi.android.geophysics.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val adapter: UrlListAdapter = UrlListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_start,
            container, false
        )
        initView()
        return binding.root
    }

    private fun initView() {
        binding.startBtn.setOnClickListener {
            if (adapter.getUrlList().isNullOrEmpty()) {
                view?.rootView?.let { view ->
                    Snackbar.make(
                        view,
                        getString(R.string.empty_error),
                        Snackbar.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
            }
            val sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
            val list = adapter.getUrlList().map {
                it.url
            }
            list.forEach {
                if (!URLUtil.isHttpsUrl(it)) {
                    view?.rootView?.let { view ->
                        Snackbar.make(
                            view,
                            getString(R.string.url_error),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    return@setOnClickListener
                }
            }
            sharedViewModel.urlList.value = list as ArrayList<String>
            this.findNavController().navigate(R.id.action_startFragment_to_mainFragment)
        }
        binding.fab.setOnClickListener {
            adapter.addList(UrlModel(""))
        }
        val layoutManager = LinearLayoutManager(activity)
        binding.urlRecycler.adapter = adapter
        binding.urlRecycler.layoutManager = layoutManager
        adapter.addList(UrlModel("https://vincetti.ru/android/geo.json"))
        adapter.addList(UrlModel("https://vincetti.ru/android/geo.json"))
    }
}
