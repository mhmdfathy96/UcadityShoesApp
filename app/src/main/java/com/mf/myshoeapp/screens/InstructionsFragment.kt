package com.mf.myshoeapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.mf.myshoeapp.R
import com.mf.myshoeapp.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {
    lateinit var instructionDataBinding: FragmentInstructionsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        instructionDataBinding=DataBindingUtil.inflate(inflater,
            R.layout.fragment_instructions, container, false)
        instructionDataBinding.btnGoToStore.setOnClickListener {
            val action = InstructionsFragmentDirections.actionInstructionsFragmentToShoesListFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
        return instructionDataBinding.root
    }
}