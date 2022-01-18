package com.example.deswita.ui.reviews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.ActivityReviewsBinding
import com.example.deswita.models.Destination
import com.example.deswita.models.Review
import com.example.deswita.ui.reviews.adapters.ReviewAdapter

class ReviewsActivity : AppCompatActivity() {

    private var _binding: ActivityReviewsBinding? = null
    private val binding get() = _binding
    private var adapter: ReviewAdapter? = null
    private var destination: Destination? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews)
        _binding = ActivityReviewsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        destination = intent.getParcelableExtra(AddReviewActivity.EXTRA_DESTINATION)

        adapter = ReviewAdapter(this)

        binding?.btnBack?.setOnClickListener {
            onBackPressed()
        }

        binding?.tvGiveReview?.setOnClickListener {
            val intent = Intent(this@ReviewsActivity,AddReviewActivity::class.java)
            intent.putExtra(AddReviewActivity.EXTRA_DESTINATION,destination)
            startActivity(intent)
        }

        initRecyclerview()
    }

    private fun initRecyclerview() {
        binding?.rvReviews?.layoutManager = LinearLayoutManager(this)
        binding?.rvReviews?.setHasFixedSize(true)
        binding?.rvReviews?.adapter = adapter


        val reviews = listOf<Review>(
            Review("Fiqri ardians","user_1","1 minggu lalu","Tempat bersejarah, belajar sejarah, salah satu icon sejarah suku tionghoa datang ke kota medan pada zaman kesultanan Deli Medan",3.5f),
            Review("Farhan","user_2","2 minggu lalu","Sejarah orang paling kaya di medan, ngumpulin kotoran manusia untuk dijadikan pupuk, dimanfaatkan oleh belanda untuk pertanian dan perkebunan, berasa masuk scene film vampir cima jaman dulu, kece semua spot fotonya",4.5f),
            Review("Rizky","post_1","1 hari lalu","Tempat bersejarah, belajar sejarah, salah satu icon sejarah suku tionghoa datang ke kota medan pada zaman kesultanan Deli Medan",4f),
            Review("Rizky","post_1","1 hari lalu","Tempat bersejarah, belajar sejarah, salah satu icon sejarah suku tionghoa datang ke kota medan pada zaman kesultanan Deli Medan",4f),
            Review("Rizky","post_1","1 hari lalu","Tempat bersejarah, belajar sejarah, salah satu icon sejarah suku tionghoa datang ke kota medan pada zaman kesultanan Deli Medan",4f),
            Review("Rizky","post_1","1 hari lalu","Tempat bersejarah, belajar sejarah, salah satu icon sejarah suku tionghoa datang ke kota medan pada zaman kesultanan Deli Medan",4f),
            Review("Rizky","post_1","1 hari lalu","Tempat bersejarah, belajar sejarah, salah satu icon sejarah suku tionghoa datang ke kota medan pada zaman kesultanan Deli Medan",4f),
        )

        adapter?.setData(reviews)
    }
}