package com.example.payskytask.modules.cards.recharge_card.domain.repository

import com.example.payskytask.modules.cards.recharge_card.domain.entity.param.RechargeCardParam

interface RechargeCardRepository {
    suspend fun rechargeCard(rechargeCardParam: RechargeCardParam)
}