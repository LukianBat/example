package com.movavi.android.geophysics.main


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.movavi.android.geophysics.R
import com.movavi.android.geophysics.databinding.FragmentMainBinding
import com.movavi.android.geophysics.databinding.FragmentResultBinding

/**
 * Fragment for result.
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,false)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.isCalculating.observe(this, Observer {
            if (it) {
                binding.mainLoadContainer.setCardBackgroundColor(Color.GRAY)
                binding.mainLoadProgress.visibility = View.INVISIBLE
                binding.mainCalcContainer.setCardBackgroundColor(Color.WHITE)
                binding.mainCalcProgress.visibility = View.VISIBLE
                binding.mainBtnResult.isEnabled = true
            }
        })

        binding.mainBtnResult.setOnClickListener {
            openResult()
        }

        return binding.root
    }

    private fun openResult(){
        this.findNavController().navigate(R.id.action_mainFragment_to_resultFragment)
    }
}
