package com.example.currencyexchange.model

data class ExchangeModel(
    val date: String,
    val info: İnfo,
    val query: Query,
    val result: Double,
    val success: Boolean
)