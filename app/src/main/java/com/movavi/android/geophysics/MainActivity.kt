package com.movavi.android.geophysics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.movavi.android.geophysics.core.SharedViewModel
import com.movavi.android.geophysics.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navigation = this.findNavController(R.id.myNavHostFragment)
        val sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)
    }

}
