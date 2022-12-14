package com.example.currencyexchange.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.currencyexchange.BuildConfig
import com.example.currencyexchange.databinding.ActivityMainBinding
import com.example.currencyexchange.model.ExchangeModel
import com.example.currencyexchange.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding?= null
    private lateinit var binding : ActivityMainBinding

    private lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.spFrom.setSelection(0)
        binding.spTo.setSelection(1)

        binding.clMain.setOnClickListener {
            closeKeyBoard()
            binding.etAmount.visibility = View.GONE
            binding.etAmount.visibility = View.VISIBLE
        }

        binding.etAmount.setOnClickListener {
            enterKeyListener()
        }

        binding.button.setOnClickListener {
            try {
                val amountStr = binding.etAmount.text.toString()
                if(amountStr[0] == '.' || amountStr[amountStr.length - 1] == '.')
                    Toast.makeText(this, "Make sure you gave valid amount", Toast.LENGTH_LONG).show()
                else {
                    val toString = binding.spTo.selectedItem.toString()
                    val fromString = binding.spFrom.selectedItem.toString()
                    val amount = binding.etAmount.text.toString().toFloat()
                    binding.progressBar.visibility = View.VISIBLE
                    binding.tvResult.visibility = View.GONE

                    closeKeyBoard()

                    binding.etAmount.visibility = View.GONE
                    binding.etAmount.visibility = View.VISIBLE

                    viewmodel.refreshData(toString, fromString, amount)
                    getLiveData(toString, fromString, amount)
                }
            }catch (e:Exception){
                Toast.makeText(this, "Make sure you gave valid amount", Toast.LENGTH_LONG).show()
            }

        }

    }

    @SuppressLint("SetTextI18n")
    private fun getLiveData(toString : String, fromString: String, amount : Float) {
        viewmodel.exchange_data.observe(this, Observer { data ->
            data?.let {
                binding.progressBar.visibility = View.GONE
                binding.tvResult.visibility = View.VISIBLE
                binding.tvResult.text = amount.toString() + " " + fromString + " = " + data.result.toString() + " " + toString
                // Progress bar issue fixed
                viewmodel.exchange_data = MutableLiveData<ExchangeModel>()
            }
        })

    }
    private fun enterKeyListener() {
        binding.etAmount.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                closeKeyBoard()
                binding.etAmount.visibility = View.GONE
                binding.etAmount.visibility = View.VISIBLE
                return@OnKeyListener true
            }
            false
        })
    }
    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}