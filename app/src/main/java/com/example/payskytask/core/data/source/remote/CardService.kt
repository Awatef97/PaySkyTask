package com.example.payskytask.core.data.source.remote

import com.example.payskytask.core.data.model.CardRequestBody
import com.example.payskytask.core.data.model.CardResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CardService {
    @POST("addCardEndpoint")
    suspend fun addCard(@Body cardRequestBody: CardRequestBody)

    @POST("rechargeCardEndpoint")
    suspend fun rechargeCard(
        @Query("cardNumber") cardNumber: String,
        @Query("balance") balance: Double
    )

    @POST("removeCardEndpoint")
    suspend fun removeCard(
        @Query("cardNumber") cardNumber: String,
    )

    @GET("getAllCardsEndpoint")
    fun getAllCards(): Flow<List<CardResponse>>


}