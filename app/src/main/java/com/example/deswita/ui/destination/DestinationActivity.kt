package com.example.deswita.ui.destination

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.deswita.R
import com.example.deswita.databinding.ActivityDestinationBinding
import com.example.deswita.models.Destination
import com.example.deswita.ui.destination.adapters.GaleryAdapter
import com.example.deswita.utils.*
import com.google.android.material.chip.Chip

class DestinationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationBinding
    private lateinit var destination: Destination
    private lateinit var galeryAdapter: GaleryAdapter

    companion object {
        const val EXTRA_DESTINATION = "extra_destination"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination)
        binding = ActivityDestinationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        destination = intent.getParcelableExtra<Destination>(EXTRA_DESTINATION) as Destination
        galeryAdapter = GaleryAdapter(this)

        with(binding) {
            ivBackground.load(Utils.getImageDrawable(this@DestinationActivity,destination.image))
            tvName.text = destination.name.CapitalizeAllWord()
            tvLocation.text = destination.location.CapitalizeFirstWord()
            tvPrice.text = "Rp. 450.000"

        }

        ratingHeroChipGroupListener()
        initRecyclerviewGalery()

    }

    //    rating
    private fun ratingHeroChipGroupListener() {
//        val ratingChips = arrayListOf(binding.chipRating1,binding.chipRating2,binding.chipRating3,binding.chipRating4,binding.chipRating5)
//        ratingChips.forEachIndexed { outerIndex, outerChip ->
//            outerChip.isChecked = outerIndex < 4
//        }
    }

    private fun initRecyclerviewGalery() {
        val galeries = listOf<String>("post_1","post_2","user_1","user_2")

        binding.rvGalery.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvGalery.setHasFixedSize(true)
        binding.rvGalery.adapter = galeryAdapter

        galeryAdapter.setData(galeries)
    }

}