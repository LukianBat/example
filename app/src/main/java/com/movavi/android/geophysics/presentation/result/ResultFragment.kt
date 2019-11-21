package com.movavi.android.geophysics.presentation.result


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.movavi.android.geophysics.R
import com.movavi.android.geophysics.core.SharedViewModel
import com.movavi.android.geophysics.data.ResultsAdapter
import com.movavi.android.geophysics.databinding.FragmentResultBinding

/**
 * Fragment for result.
 */
class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_result,
            container,false)

        sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
        val recycleV = binding.resultRv
        val adapter = ResultsAdapter()
        recycleV.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        recycleV.adapter = adapter

        // get model for
        sharedViewModel.results.observe(this, Observer {
            adapter.setList(it)
        })

        return binding.root
    }
}
