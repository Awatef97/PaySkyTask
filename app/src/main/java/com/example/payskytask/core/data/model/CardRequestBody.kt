package com.example.payskytask.core.data.model

data class CardRequestBody(
    val cardNumber: String,
    val expireDate: String,
    val cardHolder: String,
    val balance: Double = 0.00,
    val logo: String,
    val cvv: String,
)