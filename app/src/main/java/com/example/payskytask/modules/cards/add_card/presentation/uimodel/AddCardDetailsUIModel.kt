package com.example.payskytask.modules.cards.add_card.presentation.uimodel

import androidx.annotation.StringRes

data class AddCardDetailsUIModel(
    val isLoading: Boolean = false,
    @StringRes val errorMsg: Int? = null,
    val isAddedSuccessfully: Boolean = false
)