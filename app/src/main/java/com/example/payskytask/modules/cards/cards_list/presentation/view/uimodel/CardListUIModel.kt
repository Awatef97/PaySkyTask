package com.example.payskytask.modules.cards.cards_list.presentation.view.uimodel

import androidx.annotation.StringRes

data class CardListUIModel (
    val isLoading: Boolean = false,
    @StringRes val errorMsg: Int? = null,
    val cardItemsUIModel: List<CardItemUIModel>? = null,
    val showRetryButton:Boolean = false
        )