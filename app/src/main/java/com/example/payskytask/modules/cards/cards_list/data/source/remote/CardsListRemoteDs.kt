package com.example.payskytask.modules.cards.cards_list.data.source.remote

import com.example.payskytask.core.data.source.remote.CardService
import javax.inject.Inject

class CardsListRemoteDs @Inject constructor(
    private val cardService: CardService
) {
     fun getCards() = cardService.getAllCards()

    suspend fun removeCard(cardNumber: String) = cardService.removeCard(cardNumber)
}