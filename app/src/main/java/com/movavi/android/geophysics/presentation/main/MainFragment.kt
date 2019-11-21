package com.movavi.android.geophysics.presentation.main


import android.graphics.Color
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
import com.movavi.android.geophysics.core.ResItem
import com.movavi.android.geophysics.core.SharedViewModel
import com.movavi.android.geophysics.databinding.FragmentMainBinding

/**
 * Fragment for result.
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var viewModel: MainViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container, false
        )

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)

        // Загрузка завершена
        viewModel.isDownloadFinished.observe(this, Observer {
            if (it) downloadFinished()
        })

        // подсчет окончен
        viewModel.isReady.observe(this, Observer {
            if (it) calculatingReady()
        })


        // заливка данных в общую viewmodel
        viewModel.listResult.observe(this, Observer {
            sharedViewModel.results.value = it as ArrayList<ResItem>
        })

        // переход к результатам
        binding.mainBtnResult.setOnClickListener {
            openResult()
        }

        return binding.root
    }

    // логика готовности оригинальных данных
    private fun downloadFinished() {
        binding.mainLoadContainer.setCardBackgroundColor(Color.GRAY)
        binding.mainLoadProgress.visibility = View.INVISIBLE
        binding.mainCalcContainer.setCardBackgroundColor(Color.WHITE)
        binding.mainCalcProgress.visibility = View.VISIBLE
    }

    // логика по готовности математики
    private fun calculatingReady() {
        binding.mainCalcProgress.visibility = View.INVISIBLE
        binding.mainCalcContainer.setCardBackgroundColor(Color.GRAY)
        binding.mainBtnResult.isEnabled = true
    }

    // данные готовы
    private fun openResult() {
        // открытие фрагмента с результатом
        this.findNavController().navigate(R.id.action_mainFragment_to_resultFragment)
    }
}
