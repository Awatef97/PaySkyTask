package com.example.payskytask.core.data.model

data class CardRequestBody(
    val cardNumber: String,
    val expireDate: String,
    val cardHolder: String,
    val balance: Float = 0.00f,
    val logo: String,
    val cvv: String,
)