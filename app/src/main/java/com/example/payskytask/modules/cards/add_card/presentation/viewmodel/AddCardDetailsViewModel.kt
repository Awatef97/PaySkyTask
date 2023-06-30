package com.example.payskytask.modules.cards.add_card.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.payskytask.R
import com.example.payskytask.modules.cards.add_card.domain.interactor.AddCardUseCase
import com.example.payskytask.modules.cards.add_card.presentation.uimodel.AddCardDetailsUIModel
import com.example.payskytask.modules.cards.core.domain.CardEntity
import com.example.payskytask.modules.cards.core.exceptions.InvalidCardHolder
import com.example.payskytask.modules.cards.core.exceptions.InvalidCardNumber
import com.example.payskytask.modules.cards.core.exceptions.InvalidCvv
import com.example.payskytask.modules.cards.core.exceptions.InvalidExpireDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AddCardDetailsViewModel @Inject constructor(
    private val addCardUseCase: AddCardUseCase
) : ViewModel() {
    val addCardDetailsUIModel = MutableLiveData<AddCardDetailsUIModel>()

    fun addCard(cardEntity: CardEntity){
        viewModelScope.launch {
            addCardDetailsUIModel.value = AddCardDetailsUIModel(isLoading =true )
            try {
                addCardUseCase(cardEntity)
                addCardDetailsUIModel.value = AddCardDetailsUIModel(isLoading =false ,errorMsg = null, isAddedSuccessfully = true)

            }catch (e:Exception){
                addCardDetailsUIModel.value = AddCardDetailsUIModel(isLoading =false ,errorMsg = e.handleError())
            }

        }
    }
    private fun Throwable.handleError():Int{
        return  when(this) {
            is InvalidCardNumber -> R.string.msg_card_number_error
            is InvalidCardHolder -> R.string.msg_card_holder_error
            is InvalidExpireDate -> R.string.msg_expire_date
            is InvalidCvv -> R.string.msg_cvv_error
            is IOException -> R.string.msg_no_internet_conection
            else -> {R.string.msg_some_thing_wrong}
        }
    }

}