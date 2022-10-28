package com.example.twofragmentcontainer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.twofragmentcontainer.FootballViewModel
import com.example.twofragmentcontainer.FootballViewModelFactory
import com.example.twofragmentcontainer.R

class MainActivity : AppCompatActivity() {
    lateinit var navController1: NavController
    lateinit var navController2: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost1 = supportFragmentManager.findFragmentById(R.id.nav_host_fragment1)
            as NavHostFragment
        navController1 = navHost1.navController

        val navHost2 = supportFragmentManager.findFragmentById(R.id.nav_host_fragment2)
                as NavHostFragment
        navController2 = navHost2.navController

    }

//    override fun onSupportNavigateUp(): Boolean {
//        return navController1.navigateUp() || navController1.navigateUp() || super.onSupportNavigateUp()
//    }
}