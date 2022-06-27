package com.example.deswita.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.deswita.R
import com.example.deswita.ui.auth.LoginActivity
import com.example.deswita.utils.DatabaseController

private var sp : SoundPool? = null
private var soundID = 0
@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    lateinit var controller: DatabaseController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        controller = DatabaseController(this)
        Handler().postDelayed({
            if(soundID != 0){
                sp?.play(soundID, .99f, .99f, 1, 0, .99f)
            }
            Thread.sleep(3000L)
            val sharedPref = this.getSharedPreferences("skipWelcomeActivity", Context.MODE_PRIVATE )
            val status = sharedPref?.getBoolean("skipWelcomeActivity", false)

            if(status == true){
                startActivity(Intent(this, LoginActivity::class.java))
            }else{
                startActivity(Intent(this, WelcomeActivity::class.java))
            }

            finish()
        }, 1000)
    }

    override fun onStart() {
        super.onStart()
        createNewSoundPool()
        soundID = sp?.load(this, R.raw.oni_ace, 1) ?: 0
        sp?.setOnLoadCompleteListener { _, i, status ->
            if(status != 0)
                Log.i("Ingfo", "Sound not loaded")
            else
                Log.i("Ingfo", "Sound loaded")
        }
    }

    private fun createNewSoundPool() {
        sp = SoundPool.Builder()
            .setMaxStreams(15)
            .build()
    }

    private fun createOldSounPoll() {
        sp = SoundPool(15, AudioManager.STREAM_MUSIC, 0)
    }

    override fun onStop() {
        super.onStop()
        sp?.release()
        sp = null
    }
}