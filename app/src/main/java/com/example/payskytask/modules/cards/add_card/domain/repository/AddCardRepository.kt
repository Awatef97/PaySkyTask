package com.example.payskytask.modules.cards.add_card.domain.repository

import com.example.payskytask.modules.cards.core.domain.CardEntity

interface AddCardRepository {
    suspend fun addCard(cardEntity: CardEntity)
}