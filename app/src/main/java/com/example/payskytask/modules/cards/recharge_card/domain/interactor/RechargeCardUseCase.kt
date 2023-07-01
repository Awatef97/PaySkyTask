package com.example.payskytask.modules.cards.recharge_card.domain.interactor

import com.example.payskytask.modules.cards.recharge_card.domain.entity.param.RechargeCardParam
import com.example.payskytask.modules.cards.recharge_card.domain.repository.RechargeCardRepository
import javax.inject.Inject

class RechargeCardUseCase @Inject constructor(
    private val rechargeCardRepository: RechargeCardRepository
) {
    suspend operator fun invoke(rechargeCardParam: RechargeCardParam) =
        rechargeCardRepository.rechargeCard(rechargeCardParam = rechargeCardParam)
}