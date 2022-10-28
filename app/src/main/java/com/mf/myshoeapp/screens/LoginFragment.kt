package com.mf.myshoeapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.mf.myshoeapp.R
import com.mf.myshoeapp.databinding.LoginFragmentBinding
import com.mf.myshoeapp.viewModels.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    lateinit var loginBinding: LoginFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginBinding.loginViewModel = loginViewModel
        loginBinding.lifecycleOwner = this
        loginBinding.btnLogin.setOnClickListener {
            onLogin()
        }
        loginBinding.btnRegister.setOnClickListener {
            onLogin()
        }

        return loginBinding.root
    }

    private fun onLogin() {
        loginViewModel.startValidating.value=true
        if (loginViewModel.isValidUser()) {
            val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
            findNavController(requireView()).navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        loginViewModel.onDestroy()
    }
}