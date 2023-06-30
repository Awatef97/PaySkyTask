package com.example.payskytask.core.data.model.mapper

import com.example.payskytask.core.data.model.CardRequestBody
import com.example.payskytask.core.data.model.CardResponse
import com.example.payskytask.core.data.source.local.database.card.dto.CardDto
import com.example.payskytask.modules.cards.core.domain.CardEntity

fun CardEntity.toCardRequestBody() =
    CardRequestBody(
        cardNumber = cardNumber,
        expireDate = expireDate,
        cardHolder = cardHolder,
        balance = balance,
        logo = logo,
        cvv = cvv
    )

fun CardEntity.toCardDto() =
    CardDto(
        cardNumber = cardNumber,
        expireDate = expireDate,
        cardHolder = cardHolder,
        balance = balance,
        logo = logo,
        cvv = cvv
    )

fun CardResponse.toCardEntity() =
    CardEntity(
        cardNumber = cardNumber,
        expireDate = expireDate,
        cardHolder = cardHolder,
        balance = balance,
        logo = logo,
        cvv = cvv
    )
fun CardDto.toCardEntity() =
    CardEntity(
        cardNumber = cardNumber,
        expireDate = expireDate,
        cardHolder = cardHolder,
        balance = balance,
        logo = logo,
        cvv = cvv
    )