package com.example.payskytask.modules.cards.recharge_card.data.repository

import com.example.payskytask.core.data.source.local.source.CardLocalDs
import com.example.payskytask.modules.cards.recharge_card.data.source.remote.RechargeCardRemoteDs
import com.example.payskytask.modules.cards.recharge_card.domain.entity.param.RechargeCardParam
import com.example.payskytask.modules.cards.recharge_card.domain.repository.RechargeCardRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RechargeCardRepositoryImp @Inject constructor(
    private val rechargeCardRemoteDs: RechargeCardRemoteDs,
    private val cardLocalDs: CardLocalDs,
    private val ioDispatcher: CoroutineDispatcher
): RechargeCardRepository {
    override suspend fun rechargeCard(rechargeCardParam: RechargeCardParam) {
        withContext(ioDispatcher) {
            try {
                rechargeCardRemoteDs.rechargeCard(
                    cardNumber = rechargeCardParam.cardNumber,
                    amount = rechargeCardParam.amount
                )
            } catch (e: Exception) {
                cardLocalDs.rechargeCard(
                    cardNumber = rechargeCardParam.cardNumber,
                    amount = rechargeCardParam.amount
                )
            }
        }
    }
}