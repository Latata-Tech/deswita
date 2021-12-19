package com.example.deswita.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.deswita.R
import com.example.deswita.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navFragment.navController
        
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment,
            R.id.eventFragment,
            R.id.storyFragment,
            R.id.profileFragment
        ))

        binding.bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfiguration)

        supportActionBar?.hide()

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() or super.onSupportNavigateUp()
    }
}