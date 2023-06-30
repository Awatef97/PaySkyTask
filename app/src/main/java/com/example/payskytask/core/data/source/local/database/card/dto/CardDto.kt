package com.example.payskytask.core.data.source.local.database.card.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.payskytask.core.data.source.local.database.CARD_TABLE

@Entity(tableName = CARD_TABLE)
data class CardDto(
    @ColumnInfo(name = "card_number")
    @PrimaryKey val cardNumber: String,
    @ColumnInfo(name = "expire_date")
    val expireDate: String,
    @ColumnInfo(name = "card_holder")
    val cardHolder: String,
    val balance: Double = 0.00,
    val logo: String,
    val cvv: String,
)