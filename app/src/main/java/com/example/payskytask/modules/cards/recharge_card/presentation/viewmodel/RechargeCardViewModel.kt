package com.example.payskytask.modules.cards.recharge_card.presentation.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.payskytask.R
import com.example.payskytask.core.application.PaySkyTaskApplication
import com.example.payskytask.modules.cards.recharge_card.domain.entity.param.RechargeCardParam
import com.example.payskytask.modules.cards.recharge_card.domain.interactor.RechargeCardUseCase
import com.example.payskytask.modules.cards.recharge_card.presentation.uimodel.RechargeCardUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RechargeCardViewModel @Inject constructor(
    private val rechargeCardUseCase: RechargeCardUseCase,
    private val savedStateHandle: SavedStateHandle
) :ViewModel(){
    val amount = ObservableField("")
    val errorAmount = ObservableField("")
    val rechargeCardUIModel = MutableLiveData<RechargeCardUIModel>()
    val enableRechargeButton = ObservableField(true)

    fun onRechargeClicked() {
        val rechargeAmount =
            if (amount.get()?.isEmpty() == true || amount.get().isNullOrBlank())
                0.0
        else amount.get()?.toDouble()

        if (validateRechargeAmount(rechargeAmount ?: 0.0)) {
            errorAmount.set("")
            rechargeCard()
        } else {
            errorAmount.set(PaySkyTaskApplication.instance.getString(R.string.recharge_amount_hint))
        }
    }

    private fun validateRechargeAmount(amount: Double): Boolean {
        return amount in 10.00..9999.00
    }

    private fun rechargeCard(){
        rechargeCardUIModel.value = RechargeCardUIModel(isLoading = true)
        enableRechargeButton.set(false)
        viewModelScope.launch {
           try {
               rechargeCardUseCase(RechargeCardParam(
                   cardNumber = savedStateHandle.get<String>("card_number") ?:"",
                   amount = amount.get()?.toDouble() ?: 0.0
               ))
               rechargeCardUIModel.value = RechargeCardUIModel(isLoading = false,isAddedSuccessfully = true)
           }catch (e:Exception){
               enableRechargeButton.set(true)
               rechargeCardUIModel.value = RechargeCardUIModel(isLoading = false,isAddedSuccessfully = false)
           }
        }
    }
}