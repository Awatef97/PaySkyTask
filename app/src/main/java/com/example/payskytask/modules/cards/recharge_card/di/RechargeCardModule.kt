package com.example.payskytask.modules.cards.recharge_card.di

import com.example.payskytask.modules.cards.recharge_card.data.repository.RechargeCardRepositoryImp
import com.example.payskytask.modules.cards.recharge_card.domain.repository.RechargeCardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RechargeCardModule {
    @ViewModelScoped
    @Binds
    internal abstract fun provideRechargeCardRepository(rechargeCardRepositoryImp: RechargeCardRepositoryImp): RechargeCardRepository
}