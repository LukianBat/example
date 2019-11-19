package com.movavi.android.geophysics

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.movavi.android.geophysics.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.isCalculating.observe(this, Observer {
            if (it) {
                binding.mainLoadContainer.setCardBackgroundColor(Color.GRAY)
                binding.mainLoadProgress.visibility = View.INVISIBLE
                binding.mainCalcContainer.setCardBackgroundColor(Color.WHITE)
                binding.mainCalcProgress.visibility = View.VISIBLE
                Toast.makeText(this, "Loading finished", Toast.LENGTH_SHORT).show()
            }

        })
    }

}
