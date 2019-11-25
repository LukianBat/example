package com.movavi.android.geophysics.presentation.downloading


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
import com.movavi.android.geophysics.databinding.FragmentDownloadingBinding

/**
 * Fragment for downloading animation.
 */
class DownloadingFragment : Fragment() {

    private lateinit var binding: FragmentDownloadingBinding

    private lateinit var viewModel: DownloadingViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_downloading,
            container, false
        )

        sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
        viewModel = ViewModelProviders.of(this).get(DownloadingViewModel::class.java)

        //Передача списка url в [viewModel]
        viewModel.urlList.value = sharedViewModel.urlList.value as ArrayList<String>


        // получение списка скважин из viewmodel и передеча в sharedViewModel
        viewModel.listData.observe(this, Observer {
            if (it.size > 0) {
                sharedViewModel.initialData.value = it
                this.findNavController().navigate(R.id.action_downloadingFragment_to_mainFragment)
            }
        })

        // обработка ошибок загрузки файлов
        viewModel.netError.observe(this, Observer {
            if (it) {
                this.findNavController()
                    .navigate(R.id.action_downloadingFragment_to_downloadingErrorFragment)
            }
        })

        return binding.root
    }
}
