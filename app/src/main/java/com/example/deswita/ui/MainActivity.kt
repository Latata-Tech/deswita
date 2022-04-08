package com.example.deswita.ui

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.deswita.R
import com.example.deswita.databinding.ActivityMainBinding
import com.example.deswita.service.WeatherService
import com.example.deswita.ui.auth.LoginActivity
import com.example.deswita.ui.mainmenu.search.SearchActivity
import com.example.deswita.ui.mainmenu.event.EventFragment
import com.example.deswita.ui.mainmenu.home.HomeFragment
import com.example.deswita.ui.mainmenu.home.HomeViewModel
import com.example.deswita.ui.mainmenu.home.fragments.AllFragment
import com.example.deswita.ui.mainmenu.home.fragments.RecommendedFragment
import com.example.deswita.ui.mainmenu.profile.ProfileFragment
import com.example.deswita.ui.mainmenu.story.StoryFragment
import com.example.deswita.ui.notification.NotificationActivity
import com.example.deswita.ui.notification.NotificationAdapter
import com.example.deswita.utils.NotificationScheduleUtils
import com.google.android.material.navigation.NavigationBarView
import java.util.*

const val EXTRA_USER = "EXTRA_USER"
const val EXTRA_PASSWORD = "EXTRA_PASSWORD"
class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: Fragment
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var username : String

    private val notificationTime = Calendar.getInstance().timeInMillis + 5000
    private var notified = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        //Dialog
        val view2 = View.inflate(this@MainActivity, R.layout.activity_holiday_dialog, null)

        if (!notified) {
            NotificationScheduleUtils().setNotification(notificationTime, this@MainActivity)
        }

        val builder  = AlertDialog.Builder(this@MainActivity)
        builder.setView(view2)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_holiday_dialog)

        val btnNantiAja = dialog.findViewById<Button>(R.id.btnCanceDialog)
        btnNantiAja?.setOnClickListener {
            dialog.dismiss()
        }

        //dialog to recommend
        val btnCek = dialog.findViewById<Button>(R.id.btnCek)
        btnCek?.setOnClickListener {
            homeViewModel.setActiveFilter(R.id.chip_filter_recommended)
            dialog.dismiss()
        }

        username = intent.getStringExtra(EXTRA_USER).toString()
        Log.i("USERNAME", username)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        fragment = HomeFragment()
        val mFragmentManager = supportFragmentManager
        mFragmentManager.beginTransaction().apply {
            add(R.id.frameContainer, fragment, fragment::class.java.simpleName)
            commit()
        }

        binding.bottomNavigationView.setOnItemSelectedListener(this)

        binding.btnAppBarSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        binding.btnAppBarNotification.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }

        startWeatherJob()
    }

    private fun startWeatherJob() {
        val serviceComponent = ComponentName(this,WeatherService::class.java)
        val jobInfo = JobInfo.Builder(100,serviceComponent)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setRequiresDeviceIdle(true)
            .setPeriodic(15*60*1000)
        val jobWeather = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobWeather.schedule(jobInfo.build())
        Toast.makeText(this,"job service jalan",Toast.LENGTH_SHORT).show()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeFragment -> {
                fragment = HomeFragment()
                replaceFragment(fragment, fragment::class.java.simpleName)
            }
            R.id.eventFragment -> {
                fragment = EventFragment()
                replaceFragment(fragment, fragment::class.java.simpleName)
            }
            R.id.storyFragment -> {
                fragment = StoryFragment()
                replaceFragment(fragment, fragment::class.java.simpleName)
            }
            R.id.profileFragment -> {
                fragment = ProfileFragment()
                replaceFragment(fragment, fragment::class.java.simpleName)
            }
        }
        return true
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameContainer, fragment, tag)
            commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, "STATE_FRAGMENT", fragment)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        fragment = supportFragmentManager.getFragment(savedInstanceState, "STATE_FRAGMENT")!!
        replaceFragment(fragment, fragment::class.java.simpleName)
    }

}