package com.example.deswita.ui.destination

import android.app.LoaderManager
import android.content.Intent
import android.content.Loader
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.deswita.R
import com.example.deswita.databinding.ActivityDestinationBinding
import com.example.deswita.models.Destination
import com.example.deswita.models.Event
import com.example.deswita.models.Review
import com.example.deswita.service.ReviewLoader
import com.example.deswita.ui.MainViewModel
import com.example.deswita.ui.destination.adapters.GaleryAdapter
import com.example.deswita.ui.reviews.adapters.ReviewAdapter
import com.example.deswita.ui.event.EventActivity
import com.example.deswita.ui.mainmenu.home.adapters.TopDestinationAdapter
import com.example.deswita.ui.mainmenu.home.adapters.TopEventAdapter
import com.example.deswita.ui.reviews.AddReviewActivity
import com.example.deswita.ui.reviews.ReviewsActivity
import com.example.deswita.utils.*

class DestinationActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {

    private lateinit var binding: ActivityDestinationBinding
    private lateinit var destination: Destination
    private lateinit var galeryAdapter: GaleryAdapter
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var destinationAdapter: TopDestinationAdapter
    private lateinit var eventAdapter: TopEventAdapter
    private lateinit var mainViewModel: MainViewModel
    private var deswitaDB : UserReviewHelperDB? = null

    companion object {
        const val EXTRA_DESTINATION = "extra_destination"
        const val RESULT_WRITE = "result_write"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination)
        binding = ActivityDestinationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        deswitaDB = UserReviewHelperDB(this)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        destination = intent.getParcelableExtra<Destination>(EXTRA_DESTINATION) as Destination
        galeryAdapter = GaleryAdapter(this)
        reviewAdapter = ReviewAdapter(this)
        destinationAdapter = TopDestinationAdapter(this)
        eventAdapter = TopEventAdapter(this)

        observeWeather()

        with(binding) {
            ivBackground.load(Utils.getImageDrawable(this@DestinationActivity,destination.image))
            tvName.text = destination.name.CapitalizeAllWord()
            tvLocation.text = destination.location.CapitalizeFirstWord()
            tvPrice.text = "Rp. 450.000"
            ratingBar.rating = destination.rating.toFloat()
        }

        loaderManager.initLoader(11, Bundle.EMPTY, this)
//        deswitaDB?.deleteAllUserReviewOnDestination(destination.id)
        initRecyclerviewReview()
        initRecyclerviewGalery()
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
            startActivityForResult(intent,0)
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

        binding.fabShare.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND

                putExtra(Intent.EXTRA_TEXT,"${destination.name.CapitalizeAllWord()} \n\n ${destination.location.CapitalizeFirstWord()} \n ${destination.distance} km")
                type = "text/plain"

                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            }
            startActivity(Intent.createChooser(shareIntent,"Pilih dong"))
        }

    }

    private fun observeWeather() {
        mainViewModel.weather.observe(this) { weather ->
            binding.tvWeatherLocation.text = weather.name
            binding.ivWeatherIcon.load("https://openweathermap.org/img/w/${weather.weather?.get(0)?.icon}.png")
            binding.tvTemp.text = "${weather.main?.temp?.minus(273)?.toInt()} °C"
            binding.tvWeatherDescription.text = weather.weather?.get(0)?.main
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

        val result = deswitaDB?.viewAllData(destination.id)
        binding.tvUlasan.text = "Ulasan (${result?.size ?: 0})"

        if(!result.isNullOrEmpty()) {
            binding.ratingBar.rating = calculateRating(result)
            reviewAdapter.setData(result)
        }

        reviewAdapter.setOnItemClickCallback(object: ReviewAdapter.OnItemClickCcallback{
            override fun onClick(review: Review,id: Int) {
//                delete item disini dan update reviewadapter setelah delete

                when(id) {
                    R.id.reviewDelete -> {
                        val result = review.id?.let { deswitaDB?.deleteReview(it) }
                        if(result == 1) {
                            val result = deswitaDB?.viewAllData(destination.id)
                            binding.tvUlasan.text = "Ulasan (${result?.size ?: 0}) "
                            if(!result.isNullOrEmpty()) {
                                reviewAdapter.setData(result)
                            }
                        }
                    }
                    R.id.reviewReport -> {
                        Toast.makeText(this@DestinationActivity,"${review.content} reported", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
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
                intent.putExtra(EXTRA_DESTINATION,destination)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 0) {
            if(resultCode == 0) {
                val result = data?.getBooleanExtra(RESULT_WRITE,false)
                if(result == true) {
                    val result = deswitaDB?.viewAllData(destination.id)
                    binding.tvUlasan.text = "Ulasan (${result?.size ?: 0}) "
                    reviewAdapter.setData(result)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initRecyclerviewReview()
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String>? {
        return ReviewLoader(this, destination.id)
    }

    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        if(data != null) {
            initRecyclerviewReview()
        }
    }

    private fun calculateRating(review: List<Review>) : Float {
        var rate = 0f;
        review.forEach {
            rate+=it.rating
        }
        return rate / review.size;
    }

    override fun onLoaderReset(loader: Loader<String>) {
    }
}