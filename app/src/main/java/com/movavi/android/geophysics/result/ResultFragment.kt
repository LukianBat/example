package com.movavi.android.geophysics.result


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.movavi.android.geophysics.R
import com.movavi.android.geophysics.databinding.FragmentResultBinding

/**
 * Fragment for result.
 */
class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    private lateinit var viewModel: ResultViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_result,
            container,false)

        viewModel = ViewModelProviders.of(this).get(ResultViewModel::class.java)

        // get model for
        viewModel.result.observe(this, Observer {
            binding.resultTxt.text = it.toString()
        })

        return binding.root
    }
}
