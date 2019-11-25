package com.movavi.android.geophysics.presentation.calculating


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.movavi.android.geophysics.R
import com.movavi.android.geophysics.core.SharedViewModel
import com.movavi.android.geophysics.databinding.FragmentCalculatingBinding

/**
 * Fragment for calculating animation.
 */
class CalculatingFragment : Fragment() {

    private lateinit var binding: FragmentCalculatingBinding

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var viewModel: CalculatingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_calculating,
            container, false
        )

        sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
        viewModel = ViewModelProviders.of(this).get(CalculatingViewModel::class.java)

        // получение исходных данных и передача на математику
        sharedViewModel.initialData.observe(this, Observer {
            viewModel.calculate(it)
        })

        // сохранение результатов и переход на соответствуюший fragment
        viewModel.isReady.observe(this, Observer {
            if (it) {
                sharedViewModel.results.value = viewModel.listResult.value
                this.findNavController().navigate(R.id.action_calculatingFragment_to_resultFragment)
            }
        })

        return binding.root
    }
}
