package com.example.payskytask.modules.cards.cards_list.data.repository

import com.example.payskytask.core.data.model.mapper.toCardEntity
import com.example.payskytask.core.data.source.local.source.CardLocalDs
import com.example.payskytask.modules.cards.cards_list.data.source.remote.CardsListRemoteDs
import com.example.payskytask.modules.cards.cards_list.domain.repository.CardsListRepository
import com.example.payskytask.modules.cards.core.domain.CardEntity
import com.example.payskytask.modules.cards.core.exceptions.EmptyCardsListException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardsListRepositoryImp @Inject constructor(
    private val cardsListRemoteDs: CardsListRemoteDs,
    private val cardLocalDs: CardLocalDs,
    private val ioDispatcher: CoroutineDispatcher
): CardsListRepository {
    override fun getCards(): Flow<List<CardEntity>> {
        return try {
                cardsListRemoteDs.getCards().map { cardDtoLis ->
                    cardDtoLis.map {  it.toCardEntity()}
                }.flowOn(ioDispatcher)
            } catch (e: Exception) {
                val cardsList = cardLocalDs.getAllCards().map { cardDtoLis ->
                    cardDtoLis.map {  it.toCardEntity()}
                    }
                cardsList.map { cardsListEntity ->
                    cardsListEntity.ifEmpty { throw EmptyCardsListException }
                }.flowOn(ioDispatcher)

        }
    }
    override suspend fun removeCard(cardNumber: String) {
        withContext(ioDispatcher){
            try {
                cardsListRemoteDs.removeCard(cardNumber)
            } catch (e: Exception) {
                cardLocalDs.removeCard(cardNumber)
            }
        }
    }
}