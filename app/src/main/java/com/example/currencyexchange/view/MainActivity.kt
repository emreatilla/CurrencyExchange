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

    private lateinit var GET: SharedPreferences
    private lateinit var SET: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GET = getSharedPreferences(packageName, MODE_PRIVATE)
        SET = GET.edit()

        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val fromstring = GET.getString("fromString", "USD").toString()
        val tostring = GET.getString("toString", "TRY").toString()
        binding.spinner2.setSelection(getIndex(binding.spinner2, fromstring))
        binding.spinner.setSelection(getIndex(binding.spinner2, tostring))


        binding.button.setOnClickListener {
            val toString = binding.spinner.selectedItem.toString()
            val fromString = binding.spinner2.selectedItem.toString()
            val amount = binding.etFrom.text.toString().toFloat()
            binding.progressBar.visibility = View.VISIBLE
            binding.tvResult.visibility = View.GONE

            binding.etFrom.visibility = View.GONE
            binding.etFrom.visibility = View.VISIBLE

            // viewmodel.refreshData("USD", "TRY", 40)

            viewmodel.refreshData(toString, fromString, amount)
            getLiveData(toString, fromString, amount)
            // viewmodel.exchange_data.value = ExchangeModel("1", İnfo(4.5, 1), Query(4.1f, "s", "s"), 4.1, true)
        }

    }

    //private method of your class
    private fun getIndex(spinner: Spinner, myString: String): Int {
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i).toString().equals(myString, ignoreCase = true)) {
                return i
            }
        }
        return 0
    }

    @SuppressLint("SetTextI18n")
    private fun getLiveData(toString : String, fromString: String, amount : Float) {
        // viewmodel.refreshData("USD", "TRY", 40)

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