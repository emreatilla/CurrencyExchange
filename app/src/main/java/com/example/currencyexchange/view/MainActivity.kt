package com.example.currencyexchange.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.currencyexchange.databinding.ActivityMainBinding
import com.example.currencyexchange.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding?= null
    private lateinit var binding : ActivityMainBinding

    private lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewmodel.refreshData("USD", "TRY", 40)
        getLiveData()

        binding.button.setOnClickListener {
            viewmodel.refreshData("USD", "TRY", 40)
            getLiveData()
        }

    }

    private fun getLiveData() {
        viewmodel.exchange_data.observe(this, Observer { data ->
            data?.let {
                binding.tvResult.text = data.result.toString()
                Log.e("HAYN", data.result.toString())
            }
        })
    }
}