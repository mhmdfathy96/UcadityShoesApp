package com.mf.myshoeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.mf.myshoeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        navController=this.findNavController(R.id.myNavHostFragment)
        setSupportActionBar(mainBinding.toolbarMain)
        NavigationUI.setupActionBarWithNavController(this,navController,getAppBarConfiguration())
    }

    private fun getAppBarConfiguration():AppBarConfiguration{
        return AppBarConfiguration(setOf(
            R.id.loginFragment,
            R.id.welcomeFragment,
            R.id.shoesListFragment
        ))
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,getAppBarConfiguration())
    }
}
