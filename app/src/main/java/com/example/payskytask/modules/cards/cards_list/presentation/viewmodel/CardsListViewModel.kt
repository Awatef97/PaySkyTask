package com.example.payskytask.modules.cards.cards_list.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.payskytask.R
import com.example.payskytask.core.application.PaySkyTaskApplication
import com.example.payskytask.modules.cards.cards_list.domain.interactor.GetAllCardsUseCase
import com.example.payskytask.modules.cards.cards_list.domain.interactor.RemoveCardUseCase
import com.example.payskytask.modules.cards.cards_list.presentation.view.uimodel.CardListUIModel
import com.example.payskytask.modules.cards.cards_list.presentation.view.uimodel.mapper.toCardItemUIModel
import com.example.payskytask.modules.cards.core.exceptions.EmptyCardsListException
import dagger.hilt.android.lifecycle.HiltViewModel
import isInternetAvailable
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsListViewModel@Inject constructor(
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val removeCardsUseCase: RemoveCardUseCase
): ViewModel() {
    val cardListUIModel = MutableLiveData<CardListUIModel>()

    fun getCardsLis(){
        cardListUIModel.value = CardListUIModel(isLoading = true)
        if (!PaySkyTaskApplication.instance.isInternetAvailable())
            cardListUIModel.value = CardListUIModel(isLoading = false, errorMsg = R.string.msg_no_internet_conection, showRetryButton = true )
        else {
            viewModelScope.launch {
                try {
                    getAllCardsUseCase.getAllCards().collectLatest { cardListEntity ->
                        cardListUIModel.value = CardListUIModel(
                            isLoading = false,
                            errorMsg = null,
                            cardItemsUIModel = cardListEntity.map {
                                it.toCardItemUIModel()
                            })

                    }

                } catch (e: Exception) {
                    cardListUIModel.value =
                        CardListUIModel(isLoading = false, errorMsg = e.handleError())
                }

            }
        }
    }
    fun removeCard(cardNumber: String){
        cardListUIModel.value = CardListUIModel(isLoading = true)
        viewModelScope.launch {
            try {
                removeCardsUseCase.removeCard(cardNumber)
                cardListUIModel.value =
                    CardListUIModel(isLoading = false, errorMsg = null)
            } catch (e: Exception) {
                cardListUIModel.value =
                    CardListUIModel(isLoading = false, errorMsg = e.handleError())
            }

        }
    }
    private fun Throwable.handleError():Int{
        return  when(this) {
            is EmptyCardsListException -> R.string.msg_some_thing_wrong_add_new_card
            else -> {
                R.string.msg_some_thing_wrong}
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()

    }
}