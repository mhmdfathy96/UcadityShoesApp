package com.mf.myshoeapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.mf.myshoeapp.R
import com.mf.myshoeapp.databinding.LoginFragmentBinding
import com.mf.myshoeapp.viewModels.LoginViewModel

class LoginFragment : Fragment() {

    lateinit var loginViewModel: LoginViewModel
    lateinit var loginBinding: LoginFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginBinding.loginViewModel = loginViewModel
        loginBinding.lifecycleOwner = this
//       clearErrors()
        loginViewModel.onLoginPressed.observe(viewLifecycleOwner, Observer { isLogin ->
            if(isLogin){
                if (isValid()) {
                    onLogin()
                }
            }
        })

        return loginBinding.root
    }

    private fun onLogin() {
        val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
        findNavController(requireView()).navigate(action)
    }

    private fun isValid(): Boolean {
        clearErrors()
        var isValid = true
        if (!loginViewModel.isEmailValid(getEmail())) {
            isValid = false
            loginBinding.tilEmail.error = getString(R.string.email_validation)
            loginBinding.tilEmail.isErrorEnabled = true
        }
        if (!loginViewModel.isPasswordValid(getPassword())) {
            isValid = false
            loginBinding.tilPassword.error = getString(R.string.password_validation)
            loginBinding.tilPassword.isErrorEnabled = true
        }
        return isValid
    }

    private fun getEmail(): String {
        return loginBinding.tilEmail.editText!!.text.toString()
    }

    private fun getPassword(): String {
        return loginBinding.tilPassword.editText!!.text.toString()
    }


    private fun clearErrors() {
        loginBinding.tilEmail.error = null
        loginBinding.tilEmail.isErrorEnabled = false
        loginBinding.tilPassword.error = null
        loginBinding.tilPassword.isErrorEnabled = false
    }
}