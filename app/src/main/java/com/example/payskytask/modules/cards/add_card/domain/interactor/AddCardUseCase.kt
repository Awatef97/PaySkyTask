package com.example.payskytask.modules.cards.add_card.domain.interactor

import com.example.payskytask.modules.cards.add_card.domain.repository.AddCardRepository
import com.example.payskytask.modules.cards.core.domain.CardEntity
import com.example.payskytask.modules.cards.core.exceptions.InvalidCardHolder
import com.example.payskytask.modules.cards.core.exceptions.InvalidCardNumber
import com.example.payskytask.modules.cards.core.exceptions.InvalidCvv
import com.example.payskytask.modules.cards.core.exceptions.InvalidExpireDate
import javax.inject.Inject

class AddCardUseCase @Inject constructor(
    private val addCardRepository: AddCardRepository,
){
    suspend operator fun invoke(cardEntity: CardEntity){
        when{
            cardEntity.cardNumber.length != 16 -> throw (InvalidCardNumber)
            cardEntity.cardHolder.isEmpty() || cardEntity.cardHolder.isBlank() -> throw InvalidCardHolder
            !(cardEntity.expireDate.matches(Regex("""^(0[1-9]|1[0-2])\/([0-9]{2})$""")))||
                    cardEntity.expireDate.isEmpty()
                    -> throw InvalidExpireDate
            cardEntity.cvv.length !=3 -> throw InvalidCvv
            else->  addCardRepository.addCard(cardEntity)
        }

    }

}
