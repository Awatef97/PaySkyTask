package com.example.payskytask.core.data.source.local.source

import com.example.payskytask.core.data.source.local.database.PaySkyTaskDatabase
import com.example.payskytask.core.data.source.local.database.card.dto.CardDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardLocalDs @Inject constructor(
    private val paySkyTaskDatabase: PaySkyTaskDatabase
){
    suspend fun insertCard(cardDto: CardDto) =
        paySkyTaskDatabase.cardDao().insertCard(cardDto)

    suspend fun rechargeCard(cardNumber: String, amount: Double) =
        paySkyTaskDatabase.cardDao().rechargeCard(cardNumber = cardNumber, balance = amount)

    suspend fun removeCard(cardNumber: String) =
        paySkyTaskDatabase.cardDao().removeCard(cardNumber = cardNumber)

     fun getAllCards(): Flow<List<CardDto>> =
        paySkyTaskDatabase.cardDao().getAllCards()
}