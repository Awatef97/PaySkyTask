package com.example.payskytask.modules.cards.add_card.data.repository

import com.example.payskytask.core.data.model.mapper.toCardDto
import com.example.payskytask.core.data.model.mapper.toCardRequestBody
import com.example.payskytask.core.data.source.local.source.CardLocalDs
import com.example.payskytask.modules.cards.add_card.data.source.remote.AddCardRemoteDs
import com.example.payskytask.modules.cards.add_card.domain.repository.AddCardRepository
import com.example.payskytask.modules.cards.core.domain.CardEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddCardRepositoryImp @Inject constructor(
    private val addCardRemoteDs: AddCardRemoteDs,
    private val cardLocalDs: CardLocalDs,
    private val ioDispatcher: CoroutineDispatcher
) : AddCardRepository {
    override suspend fun addCard(cardEntity: CardEntity) {
        withContext(ioDispatcher){
            try {
                addCardRemoteDs.addCard(cardEntity.toCardRequestBody())
            }catch (e: Exception){
                cardLocalDs.insertCard(cardDto = cardEntity.toCardDto())
            }
        }

    }
}