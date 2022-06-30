package com.example.deswita.ui.mainmenu.story

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.deswita.R
import com.example.deswita.databinding.ActivityAddDestinationBinding
import com.example.deswita.databinding.ActivityAddStoryBinding
import com.example.deswita.databinding.ActivityCommentStoryBinding

class CommentStoryActivity : AppCompatActivity() {

    private var _binding: ActivityCommentStoryBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_story)

        _binding = ActivityCommentStoryBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        binding?.btnBack?.setOnClickListener {
            onBackPressed()
        }

        binding?.btnKirim?.setOnClickListener {
            Toast.makeText(this, "Komentar ditambahkan", Toast.LENGTH_SHORT).show()
        }
    }

}

