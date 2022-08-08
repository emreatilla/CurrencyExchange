package com.example.currencyexchange.service

import com.example.currencyexchange.model.ExchangeModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

// https://api.apilayer.com/exchangerates_data/convert?to=EUR&from=TRY&amount=10&apikey=mDfGcCNE1S8boioyjaem6cgWEhFL3Ubz

interface ExchangeAPI {
    @GET("exchangerates_data/convert?to=EUR&from=TRY&amount=10&apikey=mDfGcCNE1S8boioyjaem6cgWEhFL3Ubz")
    fun getData(
        @Query("to") toCurrency: String,
        @Query("from") fromCurrency: String,
        @Query("amount") amountCurrency: Int
    ): Single<ExchangeModel>
}