package com.example.payskytask.modules.cards.cards_list.domain.interactor

import com.example.payskytask.modules.cards.cards_list.domain.repository.CardsListRepository
import javax.inject.Inject

class RemoveCardUseCase@Inject constructor(
    private val cardsListRepository: CardsListRepository
)  {
    suspend fun removeCard(cardNumber: String) =
        cardsListRepository.removeCard(cardNumber = cardNumber)
}