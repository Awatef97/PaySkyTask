package com.example.payskytask.modules.cards.cards_list.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.payskytask.R
import com.example.payskytask.databinding.FragmentCardsListBinding
import com.example.payskytask.modules.cards.cards_list.presentation.view.uimodel.CardListUIModel
import com.example.payskytask.modules.cards.cards_list.presentation.viewmodel.CardsListViewModel
import createAlertDialog
import dagger.hilt.android.AndroidEntryPoint
import handleVisibility
import javax.inject.Inject

@AndroidEntryPoint
class CardsListFragment: Fragment() {
    private  var _binding: FragmentCardsListBinding? = null
    private val binding get() = _binding!!
    private val cardsListViewModel: CardsListViewModel by viewModels()
    @Inject
    lateinit var cardsListAdapter: CardsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardsListBinding.inflate(inflater, container, false)
        binding.toolbar.inflateMenu(R.menu.menu_add)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        initObservation()
        initRecyclerView()
        initListener()
    }

    override fun onResume() {
        super.onResume()
        getAllCardsList()
    }
    private fun getAllCardsList() = cardsListViewModel.getCardsLis()
    private fun initObservation() = with(cardsListViewModel) {
        cardListUIModel.observe(viewLifecycleOwner, ::render)
    }

    private fun render(cardListUIModel: CardListUIModel) {
        with(cardListUIModel){
            binding.progressBar.handleVisibility(isLoading)
            errorMsg?.let {
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = getString(it)
            } ?: kotlin.run { binding.tvError.visibility = View.GONE }
            cardsListAdapter.submitList(cardItemsUIModel)
            binding.btnRetry.handleVisibility(showRetryButton)
        }
    }
    private fun setUpViews(){
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_add -> {
                    findNavController().navigate(CardsListFragmentDirections.actionCardsListFragmentToAddCardDetailsFragment())
                    true
                }
                else -> false
            }
        }
    }
    private fun initListener(){
        cardsListAdapter.onItemClickedRemove = { cardNumber ->
            requireContext().createAlertDialog(
                title = getString(R.string.label_confirm_delete_card),
                message = getString(R.string.msg_confirm_delete_card),
                positiveText = getString(R.string.label_ok),
                negativeText = getString(R.string.label_cancel) ,
                positiveButtonListener ={
                    cardsListViewModel.removeCard(cardNumber = cardNumber)
                } ,
            )
        }
        cardsListAdapter.onItemClickedRechargeAmount = { cardNumber ->
            findNavController().navigate(
                CardsListFragmentDirections.actionCardsListFragmentToRechargeAmountDialogFragment(
                    cardNumber = cardNumber
                )
            )
        }

        binding.btnRetry.setOnClickListener { getAllCardsList() }
    }
    private fun initRecyclerView() {
        with(binding.rvCards) {
            adapter = cardsListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}