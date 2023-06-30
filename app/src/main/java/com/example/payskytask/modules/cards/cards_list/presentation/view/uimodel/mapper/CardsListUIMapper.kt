package com.example.payskytask.modules.cards.cards_list.presentation.view.uimodel.mapper

import com.example.payskytask.modules.cards.cards_list.presentation.view.uimodel.CardItemUIModel
import com.example.payskytask.modules.cards.core.domain.CardEntity

fun CardEntity.toCardItemUIModel() = CardItemUIModel(
    cardNumber = cardNumber,
    expireDate = expireDate,
    cardHolder = cardHolder,
    balance = balance,
    logo = logo
)