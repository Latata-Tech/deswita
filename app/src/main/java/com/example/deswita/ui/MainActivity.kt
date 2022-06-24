package com.example.deswita.ui

import android.app.LoaderManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.*
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.deswita.R
import com.example.deswita.databinding.ActivityMainBinding
import com.example.deswita.models.Destination
import com.example.deswita.models.Event
import com.example.deswita.models.weatherResponse
import com.example.deswita.service.ExampleService
import com.example.deswita.service.NotificationSchedulerService
import com.example.deswita.service.WeatherLoader
import com.example.deswita.service.WeatherServiceNew
import com.example.deswita.ui.destination.DestinationActivity
import com.example.deswita.ui.event.EventActivity
import com.example.deswita.ui.mainmenu.search.SearchActivity
import com.example.deswita.ui.mainmenu.event.EventFragment
import com.example.deswita.ui.mainmenu.home.HomeFragment
import com.example.deswita.ui.mainmenu.home.HomeViewModel
import com.example.deswita.ui.mainmenu.profile.ProfileFragment
import com.example.deswita.ui.mainmenu.story.StoryFragment
import com.example.deswita.ui.notification.NotificationActivity
import com.example.deswita.utils.EventHelperDB
import com.example.deswita.utils.Utils
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.material.navigation.NavigationBarView

const val EXTRA_USER = "EXTRA_USER"
const val EXTRA_PASSWORD = "EXTRA_PASSWORD"

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener, LoaderManager.LoaderCallbacks<String> {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: Fragment
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var username: String
    private var deswitaDB : EventHelperDB? = null
    private var mInterAds : InterstitialAd?= null

    private val weatherReceiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val weather = intent?.getParcelableExtra<weatherResponse>(WeatherServiceNew.EXTRA_WEATHER)
            if(weather != null) {
                mainViewModel.setWeather(weather)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this)
        var adRequires = AdRequest.Builder()
            .build()
        mInterAds = InterstitialAd(this).apply {
            adUnitId = "ca-app-pub-3940256099942544/1033173712"
        }

        mInterAds?.loadAd(adRequires)

        loaderManager.initLoader(1,Bundle.EMPTY,this)

        createNotificationChannel()

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val serviceNotificationSchedule = Intent(this, NotificationSchedulerService::class.java)
        this.startService(serviceNotificationSchedule)
        //Dialog
        val view2 = View.inflate(this@MainActivity, R.layout.activity_holiday_dialog, null)

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setView(view2)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_holiday_dialog)

        val btnNantiAja = dialog.findViewById<Button>(R.id.btnCanceDialog)
        btnNantiAja?.setOnClickListener {
            showInterstitial()
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

        val weatherIntentFilter = IntentFilter(WeatherServiceNew.EXTRA_INTENT)
        registerReceiver(weatherReceiver,weatherIntentFilter)

        deswitaDB = EventHelperDB(this)

        mainViewModel.eventDummy1.forEach {
            deswitaDB?.addEvent(it)
        }

    }

    private fun showInterstitial() {
       if (mInterAds?.isLoaded == true)
           mInterAds!!.show()
        else
           Toast.makeText(this,
               "Load Failed",Toast.LENGTH_SHORT).show()

    }

    private fun startWeatherJob() {
        val serviceComponent = ComponentName(this, WeatherServiceNew::class.java)
        val jobInfo = JobInfo.Builder(3333, serviceComponent)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setRequiresDeviceIdle(false)
            .setRequiresCharging(false)
            .setPeriodic(15 * 60 * 1000)
        val jobWeather = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobWeather.schedule(jobInfo.build())
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

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        return WeatherLoader(this)
    }

    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        sendNotification(mainViewModel.destinationDummy1.get(0))
    }

    override fun onLoaderReset(loader: Loader<String>) {

    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Event"
            val descriptionText = "New event appeared"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("asynctaskloader",name,importance).apply {
                description = descriptionText
            }
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(destination: Destination) {

        val intent = Intent(this, DestinationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(DestinationActivity.EXTRA_DESTINATION,destination)
        }

        val pendingIntent = PendingIntent.getActivity(this,0,intent,0)
        val bitmapLargeIcon = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.ic_deswita_icon)
        val bitmapImage = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.des_10)

        val builder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ic_deswita_icon)
            .setContentTitle(destination.name)
            .setContentText(destination.location)
            .setLargeIcon(bitmapLargeIcon)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmapImage))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(1010,builder.build())
        }
    }

}

