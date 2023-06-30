package com.example.payskytask.modules.cards.add_card.data.source.remote

import com.example.payskytask.core.data.model.CardRequestBody
import com.example.payskytask.core.data.source.remote.CardService
import javax.inject.Inject

class AddCardRemoteDs @Inject constructor(
    private val cardService: CardService
) {
    suspend fun addCard(cardRequestBody: CardRequestBody) = cardService.addCard(cardRequestBody)
}