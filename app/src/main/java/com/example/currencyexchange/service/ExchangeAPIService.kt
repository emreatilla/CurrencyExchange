package com.example.currencyexchange.service

import com.example.currencyexchange.model.ExchangeModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ExchangeAPIService {
    private val BASE_URL = "https://api.apilayer.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ExchangeAPI::class.java)

    fun getDataService(toCurrency: String, fromCurrency: String, amountCurrency: Float): Single<ExchangeModel>{
        return api.getData(toCurrency, fromCurrency, amountCurrency)
    }
}