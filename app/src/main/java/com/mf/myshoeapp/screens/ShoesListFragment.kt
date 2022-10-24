package com.mf.myshoeapp.screens

import android.os.Bundle
import android.view.*
import android.widget.TextView
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
        shoesListBinding=DataBindingUtil.inflate(inflater,
            R.layout.fragment_shoes_list,container,false)
        shoesListViewModel= ViewModelProvider(requireActivity())[ShoesListViewModel::class.java]
        shoesListBinding.shoesListViewModel=shoesListViewModel
        shoesListViewModel.init()

        shoesListViewModel.shoesList.observe(viewLifecycleOwner,Observer{ shoesList->
            if(shoesList.isNotEmpty()){
                for(shoe in shoesList){
                    addView(shoe)
                }
            }
        })
        shoesListViewModel.onAddNewShoe.observe(viewLifecycleOwner, Observer { onAddNewShoe->
            if(onAddNewShoe){
                navigateToAddNewShoe()
            }
        })
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.logout_menu, menu)
            }

            // TODO: NOT Working
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return NavigationUI.onNavDestinationSelected(menuItem,requireView().findNavController())
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return shoesListBinding.root
    }

    private fun addView(shoe: Shoe) {
        val inflater = LayoutInflater.from(requireContext()).inflate(R.layout.shoes_card, null)
        inflater.findViewById<TextView>(R.id.txtName).text=shoe.name
        inflater.findViewById<TextView>(R.id.txtCompany).text=shoe.company
        inflater.findViewById<TextView>(R.id.txtSize).text=shoe.size.toString()
        inflater.findViewById<TextView>(R.id.txtDescription).text=shoe.description
        shoesListBinding.shoesListContainer.addView(inflater, shoesListBinding.shoesListContainer.childCount)
    }
    private fun navigateToAddNewShoe(){
        val action = ShoesListFragmentDirections.actionShoesListFragmentToAddNewShoesFragment()
        Navigation.findNavController(requireView()).navigate(action)
    }

//    private fun navigateToLogin(){
//        val action = ShoesListFragmentDirections.actionShoesListFragmentToLoginFragment()
//        Navigation.findNavController(requireView()).navigate(action)
//    }

}