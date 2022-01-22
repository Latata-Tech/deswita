package com.example.deswita.ui.reviews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.ActivityReviewsBinding
import com.example.deswita.models.Destination
import com.example.deswita.models.Review
import com.example.deswita.ui.MainViewModel
import com.example.deswita.ui.reviews.adapters.ReviewAdapter

class ReviewsActivity : AppCompatActivity() {

    private var _binding: ActivityReviewsBinding? = null
    private val binding get() = _binding
    private var adapter: ReviewAdapter? = null
    private var destination: Destination? = null
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews)
        _binding = ActivityReviewsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

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

        adapter?.setData(mainViewModel.reviewDummy1)
    }
}