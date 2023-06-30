package com.example.payskytask.modules.cards.add_card.di

import com.example.payskytask.modules.cards.add_card.data.repository.AddCardRepositoryImp
import com.example.payskytask.modules.cards.add_card.domain.repository.AddCardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AddCardModule {
    @ViewModelScoped
    @Binds
    internal abstract fun provideAddCardRepository(addCardRepositoryImp: AddCardRepositoryImp): AddCardRepository
}