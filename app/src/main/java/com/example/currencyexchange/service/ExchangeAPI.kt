package com.example.currencyexchange.service

import com.example.currencyexchange.BuildConfig
import com.example.currencyexchange.model.ExchangeModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.nio.channels.spi.AbstractSelectionKey

// https://api.apilayer.com/exchangerates_data/convert?to=EUR&from=TRY&amount=10&apikey=mDfGcCNE1S8boioyjaem6cgWEhFL3Ubz

interface ExchangeAPI {
    @GET("exchangerates_data/convert?&")
    fun getData(
        @Query("to") toCurrency: String,
        @Query("from") fromCurrency: String,
        @Query("amount") amountCurrency: Float,
        @Query("apikey") apiKey: String
    ): Single<ExchangeModel>
}