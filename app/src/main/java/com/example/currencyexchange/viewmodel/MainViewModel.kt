package com.example.currencyexchange.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyexchange.model.ExchangeModel
import com.example.currencyexchange.service.ExchangeAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel: ViewModel() {
    private val exchangeAPIService = ExchangeAPIService()
    private val disposable = CompositeDisposable()

    val exchange_data = MutableLiveData<ExchangeModel>()
    val exchange_error = MutableLiveData<Boolean>()
    val exchange_load = MutableLiveData<Boolean>()

    fun refreshData(toCurrency: String, fromCurrency: String, amountCurrency: Float) {
        getDataFromAPI(toCurrency, fromCurrency, amountCurrency)
    }

    private fun getDataFromAPI(toCurrency: String, fromCurrency: String, amountCurrency: Float){
        exchange_load.value = true
        disposable.add(
            exchangeAPIService.getDataService(toCurrency, fromCurrency, amountCurrency)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<ExchangeModel>(){
                    override fun onSuccess(t: ExchangeModel){
                        exchange_data.value = t
                        exchange_error.value = false
                        exchange_load.value = false
                    }

                    override fun onError(e: Throwable) {
                        Log.e("onErr", e.toString())
                        exchange_error.value = true
                        exchange_load.value = true
                    }
                } )
        )

    }

}