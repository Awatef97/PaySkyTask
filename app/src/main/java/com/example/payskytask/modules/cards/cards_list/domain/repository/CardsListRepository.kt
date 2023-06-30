package com.example.payskytask.modules.cards.cards_list.domain.repository

import com.example.payskytask.modules.cards.core.domain.CardEntity
import kotlinx.coroutines.flow.Flow

interface CardsListRepository {
    fun getCards(): Flow<List<CardEntity>>

    suspend fun removeCard(cardNumber: String)
}