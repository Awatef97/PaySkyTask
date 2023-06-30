package com.example.payskytask.modules.cards.cards_list.presentation.view.uimodel

data class CardItemUIModel (
    val cardNumber: String,
    val expireDate: String,
    val cardHolder: String,
    val balance: Double = 0.00,
    val logo: String
        )