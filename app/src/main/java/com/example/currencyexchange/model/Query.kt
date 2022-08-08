package com.example.currencyexchange.model

data class Query(
    val amount: Int,
    val from: String,
    val to: String
)