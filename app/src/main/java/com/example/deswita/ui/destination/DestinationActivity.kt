package com.example.deswita.ui.destination

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.deswita.R
import com.example.deswita.databinding.ActivityDestinationBinding
import com.example.deswita.models.Destination
import com.example.deswita.models.Event
import com.example.deswita.models.Review
import com.example.deswita.ui.destination.adapters.GaleryAdapter
import com.example.deswita.ui.reviews.adapters.ReviewAdapter
import com.example.deswita.ui.event.EventActivity
import com.example.deswita.ui.mainmenu.home.adapters.TopDestinationAdapter
import com.example.deswita.ui.mainmenu.home.adapters.TopEventAdapter
import com.example.deswita.ui.reviews.AddReviewActivity
import com.example.deswita.ui.reviews.ReviewsActivity
import com.example.deswita.utils.*

class DestinationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationBinding
    private lateinit var destination: Destination
    private lateinit var galeryAdapter: GaleryAdapter
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var destinationAdapter: TopDestinationAdapter
    private lateinit var eventAdapter: TopEventAdapter

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
        reviewAdapter = ReviewAdapter(this)
        destinationAdapter = TopDestinationAdapter(this)
        eventAdapter = TopEventAdapter(this)

        with(binding) {
            ivBackground.load(Utils.getImageDrawable(this@DestinationActivity,destination.image))
            tvName.text = destination.name.CapitalizeAllWord()
            tvLocation.text = destination.location.CapitalizeFirstWord()
            tvPrice.text = "Rp. 450.000"

        }

        ratingHeroChipGroupListener()
        initRecyclerviewGalery()
        initRecyclerviewReview()
        initialRecyclerViewTopDestination()
        initialRecyclerViewTopEvent()


        binding.tvLookAllComment.setOnClickListener {
            val intent = Intent(this@DestinationActivity,ReviewsActivity::class.java)
            intent.putExtra(AddReviewActivity.EXTRA_DESTINATION,destination)
            startActivity(intent)
        }
        binding.btnGiveReview.setOnClickListener {
            val intent = Intent(this@DestinationActivity,AddReviewActivity::class.java)
            intent.putExtra(AddReviewActivity.EXTRA_DESTINATION,destination)
            startActivity(intent)
        }

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

    private fun initRecyclerviewReview() {

        val reviews = listOf<Review>(
            Review("Fiqri ardians","user_1","1 minggu lalu","Tempat bersejarah, belajar sejarah, salah satu icon sejarah suku tionghoa datang ke kota medan pada zaman kesultanan Deli Medan",3.5f),
            Review("Farhan","user_2","2 minggu lalu","Sejarah orang paling kaya di medan, ngumpulin kotoran manusia untuk dijadikan pupuk, dimanfaatkan oleh belanda untuk pertanian dan perkebunan, berasa masuk scene film vampir cima jaman dulu, kece semua spot fotonya",4.5f),
            Review("Rizky","post_1","1 hari lalu","Tempat bersejarah, belajar sejarah, salah satu icon sejarah suku tionghoa datang ke kota medan pada zaman kesultanan Deli Medan",4f),
        )

        binding.rvReview.layoutManager = LinearLayoutManager(this)
        binding.rvReview.setHasFixedSize(true)
        binding.rvReview.adapter = reviewAdapter

        reviewAdapter.setData(reviews)

    }

    private fun initialRecyclerViewTopDestination() {
        binding.rvDestination.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
        binding.rvDestination.setHasFixedSize(true)
        binding.rvDestination.adapter = destinationAdapter

        val destinations = listOf(
            Destination("post_1","Taman Mora Indah","Jl. Sisingamangaraja, Bangun Mulia, Kec. Medan Amplas, Kota Medan, Sumatera Utara.",false,5.0,34.4),
            Destination("post_2","Danau Toba","Bukit Barisan, Kabupaten Toba Samosir, Sumatera Utara.",false,3.5,2323.2),
            Destination("post_1","Menara Pandang Tele","Turpuk Limbong, Kec. Harian, Kab. Samosir, Sumatra Utara.",true,4.5,232.33),
            Destination("post_2","Geosite Sipinsur","Parulohan, Kec. Paranginan, Kab. Humbang Hasundutan, Sumatra Utara.",false,3.4,232.3),
            Destination("post_1","Danau Linting","Jl. Sinembah Tj. Kec. Muda Hulu, Kab. Deli Serdang, Sumatera Utara.",true,5.0,2.2),
        )

        destinationAdapter.setData(destinations)

        destinationAdapter.setOnClickItemCallback(object: TopDestinationAdapter.OnClickItemCallback {
            override fun onClick(destination: Destination) {
                val intent = Intent(this@DestinationActivity,DestinationActivity::class.java)
                intent.putExtra(DestinationActivity.EXTRA_DESTINATION,destination)
                startActivity(intent)
            }
        })

    }

    private fun initialRecyclerViewTopEvent() {
        binding.rvEvents.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
        binding.rvEvents.setHasFixedSize(true)
        binding.rvEvents.adapter = eventAdapter


        val events = listOf(
            Event(1,"post_1","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(2,"post_2","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(3,"post_1","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(2,"post_2","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(3,"post_1","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(4,"post_2","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
        )

        eventAdapter.setData(events)

        eventAdapter.setOnClickItemCallback(object: TopEventAdapter.OnClickItemCallback {
            override fun onClick(event: Event) {
                val intent = Intent(this@DestinationActivity, EventActivity::class.java)
                intent.putExtra(EventActivity.EXTRA_EVENT,event)
                startActivity(intent)
            }
        })


    }

}