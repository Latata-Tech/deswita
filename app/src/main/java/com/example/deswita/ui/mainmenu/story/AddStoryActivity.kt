package com.example.deswita.ui.mainmenu.story

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.deswita.R
import com.example.deswita.databinding.ActivityAddStoryBinding
import java.util.*
import kotlin.concurrent.schedule
import android.net.Uri


class AddStoryActivity : AppCompatActivity() {

    private var _binding: ActivityAddStoryBinding? = null
    private val binding get() = _binding

    private val requestCodeStory = 200
    private var imageUri : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_story)
        _binding = ActivityAddStoryBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding?.btnSelectImage?.setOnClickListener{
            selectImage()
        }

        binding?.btnBack?.setOnClickListener {
            onBackPressed()
        }

        binding?.btnAddStory?.setOnClickListener {
            Toast.makeText(this, "Add story!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun selectImage() {
       var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, requestCodeStory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == requestCodeStory){
            Thread(Runnable {
                imageUri = data?.data
                Timer().schedule(900){
                    binding?.ivAddStory?.post{
                        binding?.ivAddStory?.setImageURI(imageUri)
                    }
                }
            }).start()
        }
    }
}