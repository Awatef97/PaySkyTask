package com.example.payskytask.modules.cards.recharge_card.presentation.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.payskytask.R
import com.example.payskytask.databinding.DialogFragmentRechargeBinding
import com.example.payskytask.modules.cards.recharge_card.presentation.uimodel.RechargeCardUIModel
import com.example.payskytask.modules.cards.recharge_card.presentation.viewmodel.RechargeCardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RechargeAmountDialogFragment: DialogFragment() {

    private val rechargeCardViewModel: RechargeCardViewModel by viewModels()
    private  lateinit var binding: DialogFragmentRechargeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.dialog_fragment_recharge, container, false
        )
        binding.viewModel = rechargeCardViewModel
        binding.lifecycleOwner = this
        isCancelable = false
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = rechargeCardViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initObservation()
        binding.ivClose.setOnClickListener {
            dialog?.dismiss()
        }
    }

    private fun initObservation()= with(rechargeCardViewModel){
        binding.lifecycleOwner?.let { rechargeCardUIModel.observe(it,::render) }
    }

    private fun render(rechargeCardUIModel: RechargeCardUIModel) {
        with(rechargeCardUIModel){
            if (isLoading) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE
            isAddedSuccessfully?.let {
                if (it) {
                    Toast.makeText(requireContext(),getString(R.string.msg_amount_added), Toast.LENGTH_SHORT).show()
                    dismiss()
                }
                else Toast.makeText(requireContext(),getString(R.string.msg_failed_to_add_amount), Toast.LENGTH_SHORT).show()

            }
        }
    }
    override fun onResume() {
        super.onResume()
        val width = resources.displayMetrics.widthPixels - 100
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
    }

}