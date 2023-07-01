package com.example.payskytask.modules.cards.recharge_card.data.source.remote

import com.example.payskytask.core.data.source.remote.CardService
import javax.inject.Inject

class RechargeCardRemoteDs @Inject constructor(
    private val cardService: CardService
) {
    suspend fun rechargeCard(cardNumber: String, amount: Double) =
        cardService.rechargeCard(cardNumber = cardNumber, balance = amount)
}