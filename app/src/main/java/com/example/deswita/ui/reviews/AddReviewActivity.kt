package com.example.deswita.ui.reviews

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.deswita.R
import com.example.deswita.databinding.ActivityAddReviewBinding
import com.example.deswita.models.Destination
import com.example.deswita.models.Review
import com.example.deswita.ui.destination.DestinationActivity
import com.example.deswita.utils.UserReviewHelperDB

class AddReviewActivity : AppCompatActivity() {

    private var _binding: ActivityAddReviewBinding? = null
    private val binding get() = _binding
    private var destination: Destination? = null
    private var deswita_db : UserReviewHelperDB? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_review)
        _binding = ActivityAddReviewBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        deswita_db = UserReviewHelperDB(this)

        destination = intent.getParcelableExtra(EXTRA_DESTINATION)

        binding?.btnBack?.setOnClickListener {
            onBackPressed()
        }

        binding?.btnPosting?.setOnClickListener {
            val userReview : Review = Review()
            userReview.content = binding?.editText?.text.toString()
            userReview.rating = binding?.ratingBar?.rating?.toFloat() ?: 0f
            userReview.destination_id = destination?.id ?: 0
            userReview.user_id = 1
            var result = deswita_db?.addUserReview(userReview)
            if(result!=-1L){
                Toast.makeText(this, "Berhasil memberikan review",Toast.LENGTH_SHORT).show()
                val i = Intent()
                i.putExtra(DestinationActivity.RESULT_WRITE,true)
                setResult(0,i)
                finish()
            }
            else{
                Toast.makeText(this, "Gagal memberikan review",Toast.LENGTH_SHORT).show()
                val i = Intent()
                i.putExtra(DestinationActivity.RESULT_WRITE,false)
                setResult(0,i)
                finish()
            }
        }

        binding?.tvName?.text = "Berikan penilaian untuk ${destination?.name}"
    }

    companion object {
        const val EXTRA_DESTINATION = "extra_destination"
    }
}