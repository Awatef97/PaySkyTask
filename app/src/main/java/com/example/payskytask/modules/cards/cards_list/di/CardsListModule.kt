package com.example.payskytask.modules.cards.cards_list.di

import com.example.payskytask.modules.cards.cards_list.data.repository.CardsListRepositoryImp
import com.example.payskytask.modules.cards.cards_list.domain.repository.CardsListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class CardListModule {
    @ViewModelScoped
    @Binds
    internal abstract fun provideCardListRepository(cardsListRepositoryImp: CardsListRepositoryImp): CardsListRepository
}