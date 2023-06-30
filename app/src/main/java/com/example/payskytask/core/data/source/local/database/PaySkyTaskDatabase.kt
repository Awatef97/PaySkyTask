package com.example.payskytask.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.payskytask.core.data.source.local.database.card.dao.CardDao
import com.example.payskytask.core.data.source.local.database.card.dto.CardDto

@Database(
    entities = [CardDto::class], version = 1, exportSchema = false
)
abstract class PaySkyTaskDatabase : RoomDatabase(){
    abstract fun cardDao(): CardDao
}
const val CARD_TABLE = "card_table"