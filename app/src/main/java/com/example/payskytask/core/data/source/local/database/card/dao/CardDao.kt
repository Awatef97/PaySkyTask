package com.example.payskytask.core.data.source.local.database.card.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.payskytask.core.data.source.local.database.CARD_TABLE
import com.example.payskytask.core.data.source.local.database.card.dto.CardDto
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(cardDto: CardDto)

    @Query("UPDATE $CARD_TABLE SET balance = balance + :balance WHERE card_number = :cardNumber ")
    suspend fun rechargeCard(cardNumber: String, balance: Double)

    @Query("DELETE FROM $CARD_TABLE where card_number = :cardNumber")
    suspend fun removeCard(cardNumber: String)

    @Query("Select * from $CARD_TABLE")
    fun getAllCards(): Flow<List<CardDto>>
}