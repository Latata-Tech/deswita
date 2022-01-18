package com.example.deswita.ui.reviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.deswita.R
import com.example.deswita.databinding.ActivityAddReviewBinding
import com.example.deswita.models.Destination

class AddReviewActivity : AppCompatActivity() {

    private var _binding: ActivityAddReviewBinding? = null
    private val binding get() = _binding
    private var destination: Destination? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_review)
        _binding = ActivityAddReviewBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        destination = intent.getParcelableExtra(EXTRA_DESTINATION)

        binding?.btnBack?.setOnClickListener {
            onBackPressed()
        }

        binding?.btnPosting?.setOnClickListener {
            Toast.makeText(this, "Review untuk ${destination?.name} diposting!", Toast.LENGTH_SHORT).show()
        }

        binding?.tvName?.text = "Berikan penilaian untuk ${destination?.name}"
    }

    companion object {
        const val EXTRA_DESTINATION = "extra_destination"
    }
}