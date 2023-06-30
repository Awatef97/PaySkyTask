package com.example.payskytask.modules.cards.cards_list.domain.interactor

import com.example.payskytask.modules.cards.cards_list.domain.repository.CardsListRepository
import com.example.payskytask.modules.cards.core.domain.CardEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCardsUseCase @Inject constructor(
    private val cardsListRepository: CardsListRepository
) {
     fun getAllCards(): Flow<List<CardEntity>> =
        cardsListRepository.getCards()

}