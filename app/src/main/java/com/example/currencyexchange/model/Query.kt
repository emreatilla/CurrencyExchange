package com.example.currencyexchange.model

data class Query(
    val amount: Float,
    val from: String,
    val to: String
)