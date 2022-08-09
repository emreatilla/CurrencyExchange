package com.example.currencyexchange.service

import com.example.currencyexchange.model.ExchangeModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ExchangeAPI {
    @GET("exchangerates_data/convert?&")
    fun getData(
        @Query("to") toCurrency: String,
        @Query("from") fromCurrency: String,
        @Query("amount") amountCurrency: Float,
        @Query("apikey") apiKey: String
    ): Single<ExchangeModel>
}