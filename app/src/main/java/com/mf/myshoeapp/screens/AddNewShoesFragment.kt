package com.mf.myshoeapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.mf.myshoeapp.R
import com.mf.myshoeapp.databinding.FragmentAddNewShoesBinding
import com.mf.myshoeapp.models.Shoe
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
        shoesListViewModel.init()
        shoesListViewModel.onCancel.observe(viewLifecycleOwner, Observer { onCancel ->
            if (onCancel) {
                onCancel()
            }
        })
        shoesListViewModel.onSave.observe(viewLifecycleOwner, Observer { onSave ->
            if (onSave) {
                onSave()
            }
        })
        return addNewShoesBinding.root
    }

    private fun isValidShoe(): Boolean {
        clearErrors()
        var isValid = true
        if (!shoesListViewModel.isValidString(getShoe().name)) {
            isValid = false
            addNewShoesBinding.tilName.error = getString(R.string.name_validation)
            addNewShoesBinding.tilName.isErrorEnabled = true
        }
        if (!shoesListViewModel.isValidDouble(getShoe().size)) {
            isValid = false
            addNewShoesBinding.tilShoeSize.error = getString(R.string.shoe_size_validation)
            addNewShoesBinding.tilShoeSize.isErrorEnabled = true
        }
        if (!shoesListViewModel.isValidString(getShoe().company)) {
            isValid = false
            addNewShoesBinding.tilCompany.error = getString(R.string.company_validation)
            addNewShoesBinding.tilCompany.isErrorEnabled = true
        }
        if (!shoesListViewModel.isValidString(getShoe().description)) {
            isValid = false
            addNewShoesBinding.tilDescription.error = getString(R.string.description_validation)
            addNewShoesBinding.tilDescription.isErrorEnabled = true
        }

        return isValid
    }

    private fun getShoe(): Shoe {
        fun String.fullTrim() = trim().replace("\uFEFF", "")
        return Shoe(
            addNewShoesBinding.tilName.editText!!.text.toString(),
            (addNewShoesBinding.tilShoeSize.editText!!.text.toString()).fullTrim().toDouble(),
            addNewShoesBinding.tilCompany.editText!!.text.toString(),
            addNewShoesBinding.tilDescription.editText!!.text.toString(),
            mutableListOf()
        )
    }

    private fun onSave() {
        if (isValidShoe()) {
            shoesListViewModel.newShoe=getShoe()
            shoesListViewModel.addNewShoe()
            val action = AddNewShoesFragmentDirections.actionAddNewShoesFragmentToShoesListFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
    }

    private fun onCancel() {
        val action = AddNewShoesFragmentDirections.actionAddNewShoesFragmentToShoesListFragment()
        Navigation.findNavController(requireView()).navigate(action)
    }

    private fun clearErrors() {
        addNewShoesBinding.tilName.error = null
        addNewShoesBinding.tilName.isErrorEnabled = false
        addNewShoesBinding.tilCompany.error = null
        addNewShoesBinding.tilCompany.isErrorEnabled = false
        addNewShoesBinding.tilShoeSize.error = null
        addNewShoesBinding.tilShoeSize.isErrorEnabled = false
        addNewShoesBinding.tilDescription.error = null
        addNewShoesBinding.tilDescription.isErrorEnabled = false
    }
}