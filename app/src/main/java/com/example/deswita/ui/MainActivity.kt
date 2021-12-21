package com.example.deswita.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.deswita.R
import com.example.deswita.databinding.ActivityMainBinding
import com.example.deswita.ui.mainmenu.event.EventFragment
import com.example.deswita.ui.mainmenu.home.HomeFragment
import com.example.deswita.ui.mainmenu.profile.ProfileFragment
import com.example.deswita.ui.mainmenu.story.StoryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val homeFragment = HomeFragment()
        val mFragmentManager = supportFragmentManager
        mFragmentManager.beginTransaction().apply {
            add(R.id.frameContainer,homeFragment,HomeFragment::class.java.simpleName)
            commit()
        }

        binding.bottomNavigationView.setOnItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.homeFragment -> {
                val fragment = HomeFragment()
                replaceFragment(fragment,HomeFragment::class.java.simpleName)
            }
            R.id.eventFragment -> {
                val fragment = EventFragment()
                replaceFragment(fragment,EventFragment::class.java.simpleName)
            }
            R.id.storyFragment -> {
                val fragment = StoryFragment()
                replaceFragment(fragment,StoryFragment::class.java.simpleName)
            }
            R.id.profileFragment -> {
                val fragment = ProfileFragment()
                replaceFragment(fragment,ProfileFragment::class.java.simpleName)
            }
        }
        return true
    }

    private fun replaceFragment(fragment: Fragment,tag: String) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameContainer,fragment,tag)
            commit()
        }
    }

}