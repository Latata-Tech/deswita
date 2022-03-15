package com.example.deswita.ui.destination

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.deswita.R
import com.example.deswita.databinding.ActivityDestinationBinding
import com.example.deswita.models.Destination
import com.example.deswita.models.Event
import com.example.deswita.models.Review
import com.example.deswita.ui.MainViewModel
import com.example.deswita.ui.destination.adapters.GaleryAdapter
import com.example.deswita.ui.reviews.adapters.ReviewAdapter
import com.example.deswita.ui.event.EventActivity
import com.example.deswita.ui.mainmenu.home.adapters.TopDestinationAdapter
import com.example.deswita.ui.mainmenu.home.adapters.TopEventAdapter
import com.example.deswita.ui.mainmenu.story.adapters.StoryAdapter
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
    private lateinit var mainViewModel: MainViewModel

    companion object {
        const val EXTRA_DESTINATION = "extra_destination"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination)
        binding = ActivityDestinationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

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
            ratingBar.rating = destination.rating.toFloat()
        }

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

        binding.tvLookAllGallery.setOnClickListener {
            val galleryFragment = GalleryFragment()
            galleryFragment.show(supportFragmentManager,GalleryFragment::class.java.simpleName)
        }

        binding.tvLookAllEvents.setOnClickListener {
            Toast.makeText(this, "on progress", Toast.LENGTH_SHORT).show()
        }

        binding.tvLookAllDestination.setOnClickListener {
            Toast.makeText(this, "on progress", Toast.LENGTH_SHORT).show()
        }

    }

    private fun initRecyclerviewGalery() {

        binding.rvGalery.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvGalery.setHasFixedSize(true)
        binding.rvGalery.adapter = galeryAdapter

        galeryAdapter.setData(mainViewModel.getGallery())

        galeryAdapter.setOnItemClickCallback(object: GaleryAdapter.OnItemClickCcallback{
            override fun onClick(photo: String) {
                val bundle = Bundle()
                bundle.putString(ImageFragment.IMAGE_STR,photo)
                val imageFragment = ImageFragment()
                imageFragment.arguments = bundle
                imageFragment.show(supportFragmentManager, ImageFragment::class.java.simpleName)
            }
        })
    }

    private fun initRecyclerviewReview() {

        binding.rvReview.layoutManager = LinearLayoutManager(this)
        binding.rvReview.setHasFixedSize(true)
        binding.rvReview.adapter = reviewAdapter

        reviewAdapter.setData(mainViewModel.reviewDummy2)

    }

    private fun initialRecyclerViewTopDestination() {
        binding.rvDestination.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
        binding.rvDestination.setHasFixedSize(true)
        binding.rvDestination.adapter = destinationAdapter

        destinationAdapter.setData(mainViewModel.destinationDummy4)

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

        eventAdapter.setData(mainViewModel.eventDummy3)

        eventAdapter.setOnClickItemCallback(object: TopEventAdapter.OnClickItemCallback {
            override fun onClick(event: Event) {
                val intent = Intent(this@DestinationActivity, EventActivity::class.java)
                intent.putExtra(EventActivity.EXTRA_EVENT,event)
                startActivity(intent)
            }
        })


    }

}