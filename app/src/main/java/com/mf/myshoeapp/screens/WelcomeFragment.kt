package com.mf.myshoeapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.mf.myshoeapp.R
import com.mf.myshoeapp.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    lateinit var welcomeBinding: FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        welcomeBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_welcome,container,false)
        welcomeBinding.btnLearnMore.setOnClickListener {
                val action = WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment()
                Navigation.findNavController(requireView()).navigate(action)
        }
        return welcomeBinding.root
    }

}