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
import androidx.recyclerview.widget.LinearLayoutManager
import com.movavi.android.geophysics.R
import com.movavi.android.geophysics.core.SharedViewModel
import com.movavi.android.geophysics.data.InitDataHolesAdapter
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

        val mRecycler=binding.startDatRecycler
        val mAdapter= InitDataHolesAdapter()
        mRecycler.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        mRecycler.adapter=mAdapter

        // Загрузка завершена - данные выводим
        sharedViewModel.initialData.observe(this, Observer {
            mAdapter.setList(it)
        })

        // переход к результатам
        binding.mainBtnResult.setOnClickListener {
            openResult()
        }

        return binding.root
    }

    // данные готовы
    private fun openResult() {
        // открытие фрагмента с результатом
        this.findNavController().navigate(R.id.action_mainFragment_to_calculatingFragment)
    }
}
