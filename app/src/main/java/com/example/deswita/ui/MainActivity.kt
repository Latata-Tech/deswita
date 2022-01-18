package com.example.deswita.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.deswita.R
import com.example.deswita.databinding.ActivityMainBinding
import com.example.deswita.ui.mainmenu.search.SearchActivity
import com.example.deswita.ui.mainmenu.event.EventFragment
import com.example.deswita.ui.mainmenu.home.HomeFragment
import com.example.deswita.ui.mainmenu.profile.ProfileFragment
import com.example.deswita.ui.mainmenu.story.StoryFragment
import com.example.deswita.ui.notification.NotificationActivity
import com.example.deswita.ui.notification.NotificationAdapter
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

        binding.btnAppBarSearch.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        binding.btnAppBarNotification.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }

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