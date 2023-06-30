package com.example.payskytask.modules.cards.cards_list.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.payskytask.R
import com.example.payskytask.databinding.FragmentCardsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardsListFragment: Fragment() {
    private  var _binding: FragmentCardsListBinding? = null
    private val binding get() = _binding!!

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}