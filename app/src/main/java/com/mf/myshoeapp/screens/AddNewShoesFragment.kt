package com.mf.myshoeapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.mf.myshoeapp.R
import com.mf.myshoeapp.databinding.FragmentAddNewShoesBinding
import com.mf.myshoeapp.viewModels.ShoesListViewModel

class AddNewShoesFragment : Fragment() {
    private lateinit var addNewShoesBinding: FragmentAddNewShoesBinding
    val shoesListViewModel: ShoesListViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        addNewShoesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_new_shoes, container, false)
        addNewShoesBinding.shoesListViewModel = shoesListViewModel
        addNewShoesBinding.lifecycleOwner = this
        addNewShoesBinding.btnCancel.setOnClickListener {
            onPopBack()
        }
        addNewShoesBinding.btnSave.setOnClickListener {
            shoesListViewModel.startValidating.value = true
            onSave()
        }
        return addNewShoesBinding.root
    }

    private fun onSave() {
        if (shoesListViewModel.isValidShoe()) {
            shoesListViewModel.addNewShoe()
            onPopBack()
        }
    }

    private fun onPopBack() {
        shoesListViewModel.onPopBack()
        val action = AddNewShoesFragmentDirections.actionAddNewShoesFragmentToShoesListFragment()
        Navigation.findNavController(requireView()).navigate(action)
    }
}