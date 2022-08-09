package com.example.currencyexchange.view

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.icu.text.IDNA
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.currencyexchange.databinding.ActivityMainBinding
import com.example.currencyexchange.model.ExchangeModel
import com.example.currencyexchange.model.Query
import com.example.currencyexchange.model.İnfo
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

        binding.spFrom.setSelection(0)
        binding.spTo.setSelection(1)

        binding.button.setOnClickListener {
            val toString = binding.spTo.selectedItem.toString()
            val fromString = binding.spFrom.selectedItem.toString()
            val amount = binding.etFrom.text.toString().toFloat()
            binding.progressBar.visibility = View.VISIBLE
            binding.tvResult.visibility = View.GONE

            binding.etFrom.visibility = View.GONE
            binding.etFrom.visibility = View.VISIBLE

            // viewmodel.refreshData("USD", "TRY", 40)

            viewmodel.refreshData(toString, fromString, amount)
            getLiveData(toString, fromString, amount)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun getLiveData(toString : String, fromString: String, amount : Float) {
        viewmodel.exchange_data.observe(this, Observer { data ->
            data?.let {
                binding.progressBar.visibility = View.GONE
                binding.tvResult.visibility = View.VISIBLE
                binding.tvResult.text = amount.toString() + " " + fromString + " = " + data.result.toString() + " " + toString
                Log.e("result", data.result.toString())
                // Progress bar issue fixed
                viewmodel.exchange_data = MutableLiveData<ExchangeModel>()
            }
        })

    }
}