package com.mf.myshoeapp.screens

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.mf.myshoeapp.R
import com.mf.myshoeapp.databinding.FragmentShoesListBinding
import com.mf.myshoeapp.databinding.ShoesCardBinding
import com.mf.myshoeapp.models.Shoe
import com.mf.myshoeapp.viewModels.ShoesListViewModel


class ShoesListFragment : Fragment() {
    lateinit var shoesListBinding: FragmentShoesListBinding
    lateinit var shoesListViewModel: ShoesListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        shoesListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoes_list, container, false
        )
        shoesListViewModel = ViewModelProvider(requireActivity())[ShoesListViewModel::class.java]
        shoesListBinding.shoesListViewModel = shoesListViewModel

        shoesListViewModel.shoesList.observe(viewLifecycleOwner, Observer { shoesList ->
            if (shoesList.isNotEmpty()) {
                for (shoe in shoesList) {
                    addView(shoe)
                }
            }
        })
        shoesListBinding.fab.setOnClickListener {
            navigateToAddNewShoe()
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.logout_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return NavigationUI.onNavDestinationSelected(
                    menuItem,
                    requireView().findNavController()
                )
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return shoesListBinding.root
    }

    private fun addView(shoe: Shoe) {
        val shoesCardBinding =
            ShoesCardBinding.inflate(LayoutInflater.from(requireContext()), null, false)
        shoesCardBinding.shoe = shoe
        shoesListBinding.shoesListContainer.addView(shoesCardBinding.root)
    }

    private fun navigateToAddNewShoe() {
        val action = ShoesListFragmentDirections.actionShoesListFragmentToAddNewShoesFragment()
        Navigation.findNavController(requireView()).navigate(action)
    }


}