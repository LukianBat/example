package com.movavi.android.geophysics.start


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.movavi.android.geophysics.R
import com.movavi.android.geophysics.databinding.FragmentResultBinding
import com.movavi.android.geophysics.databinding.FragmentStartBinding

/**
 * Fragment for result.
 */
class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_start,
            container,false)

        binding.startBtn.setOnClickListener {
            this.findNavController().navigate(R.id.action_startFragment_to_mainFragment)
        }

        return binding.root
    }
}
