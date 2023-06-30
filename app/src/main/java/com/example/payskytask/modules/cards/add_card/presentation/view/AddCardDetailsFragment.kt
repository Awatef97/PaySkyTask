package com.example.payskytask.modules.cards.add_card.presentation.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.payskytask.R
import com.example.payskytask.core.extension.detectCardType
import com.example.payskytask.core.extension.getCardLogoResource
import com.example.payskytask.databinding.FragmentAddCardDetailsBinding
import com.example.payskytask.modules.cards.add_card.presentation.uimodel.AddCardDetailsUIModel
import com.example.payskytask.modules.cards.add_card.presentation.viewmodel.AddCardDetailsViewModel
import com.example.payskytask.modules.cards.core.domain.CardEntity
import dagger.hilt.android.AndroidEntryPoint
import drawableToString
import handleVisibility
import validateCardHolder
import validateExpiryDate

@AndroidEntryPoint
class AddCardDetailsFragment: Fragment() {
    private  lateinit var binding: FragmentAddCardDetailsBinding
    private val addCardViewModel: AddCardDetailsViewModel by viewModels ()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_add_card_details, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        setUpViews()
        initObservation()
    }

    private fun initObservation() = with(addCardViewModel) {
        addCardDetailsUIModel.observe(viewLifecycleOwner, ::render)
    }

    private fun render(addCardDetailsUIModel: AddCardDetailsUIModel) {
        with(addCardDetailsUIModel){
            binding.progressBar.handleVisibility(isLoading)
            binding.tvError.text = errorMsg?.let { getString(it) }
            if (isAddedSuccessfully) {
                Toast.makeText(requireContext(),getString(R.string.msg_card_is_add),Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
    }


    private fun setUpViews(){
        binding.etExpiration.validateExpiryDate()
        binding.etCardHolder.validateCardHolder()


        binding.etCardNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length!! <= 2) {
                    val cardType = s.toString().detectCardType()

                    val cardLogoResId = cardType.getCardLogoResource()
                    binding.ivLogo.setImageResource(cardLogoResId)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

    }
    private fun initListener(){
        binding.btnAddCard.setOnClickListener {
         addCardViewModel.addCard(
             CardEntity(
                 cardNumber = binding.etCardNumber.text.toString(),
                 cardHolder = binding.etCardHolder.text.toString(),
                 expireDate = binding.etExpiration.text.toString(),
                 logo = binding.ivLogo.drawable.drawableToString(),
                 cvv = binding.etCvv.text.toString()
             )
         )
        }
    }


}