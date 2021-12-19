package com.example.deswita.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.deswita.R
import com.example.deswita.adapter.AdapterSliderWelcome
import com.example.deswita.ui.auth.LoginActivity
import me.relex.circleindicator.CircleIndicator

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val imgSlider = listOf<Int>(
            R.drawable.ic_slider_image_welcome_1,
            R.drawable.ic_slider_image_welcome_2,
            R.drawable.ic_slider_image_welcome_3
        )
        val titleTextSlider = listOf<String>(
            "Berwisata jadi lebih mudah",
            "Ayo promosikan wisata",
            "Bagikan cerita mu"
        )
        val detailTextSlider = listOf<String>(
            "Deswita akan membantu kamu menemukan lokasi wisata terbaik dan fitur-fitur lainnya",
            "Kamu dapat mempromosikan tempat wisata yang berpotensi, untuk mengundang wisatawan",
            "Pengalaman berwisata menjadi cerita yang seru dan menarik, kamu dapat membagikan keseruan itu"
        )
        val adapterSliderWelcome = AdapterSliderWelcome(imgSlider, titleTextSlider, detailTextSlider, this)

        val pager = findViewById<ViewPager>(R.id.pager)
        pager.adapter = adapterSliderWelcome

        val indicatorSlider = findViewById<CircleIndicator>(R.id.indicatorSlider)
        indicatorSlider.setViewPager(pager)
    }

    fun loginActivity(view : View)
    {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}