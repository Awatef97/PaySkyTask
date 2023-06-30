package com.example.payskytask.modules.cards.cards_list.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.payskytask.core.extension.base64ToDrawable
import com.example.payskytask.databinding.ItemCardListBinding
import com.example.payskytask.modules.cards.cards_list.presentation.view.uimodel.CardItemUIModel
import javax.inject.Inject

class CardsListAdapter @Inject constructor()
    : ListAdapter<CardItemUIModel, CardsListAdapter.CardListViewHolder>(DiffUtilCallback){

    var onItemClickedRemove: ((String) -> Unit) = { _ -> }
    var onItemClickedRechargeAmount: ((String) -> Unit) = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListViewHolder {
        val binding =
            ItemCardListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class CardListViewHolder(private val binding: ItemCardListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cardItemUIModel: CardItemUIModel){
            with(binding){
                tvCardNumber.text = cardItemUIModel.cardNumber
                tvCardHolder.text = cardItemUIModel.cardHolder
                tvExpireDate.text = cardItemUIModel.expireDate
                tvBalance.text = cardItemUIModel.balance.toString()
                ivLogo.setImageDrawable(cardItemUIModel.logo.base64ToDrawable())
                btnRemove.setOnClickListener {
                    onItemClickedRemove.invoke(cardItemUIModel.cardNumber)
                }
                btnAddBalance.setOnClickListener {
                    onItemClickedRechargeAmount.invoke(cardItemUIModel.cardNumber)
                }
            }
        }
        }
    private object DiffUtilCallback : DiffUtil.ItemCallback<CardItemUIModel>() {
        override fun areItemsTheSame(
            oldItem: CardItemUIModel,
            newItem: CardItemUIModel
        ): Boolean {
            return oldItem.cardNumber == newItem.cardNumber
        }

        override fun areContentsTheSame(
            oldItem: CardItemUIModel,
            newItem: CardItemUIModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}