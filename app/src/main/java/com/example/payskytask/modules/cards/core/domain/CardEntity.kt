package com.example.payskytask.modules.cards.core.domain

data class CardEntity(
    val cardNumber: String,
    val expireDate: String,
    val cardHolder: String,
    val cvv: String,
    val balance: Float = 0.00f,
    val logo: String
)
