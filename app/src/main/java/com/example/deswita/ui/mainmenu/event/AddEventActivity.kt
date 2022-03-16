package com.example.deswita.ui.mainmenu.event

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.example.deswita.R
import com.example.deswita.databinding.ActivityAddEventBinding
import java.util.*
import kotlin.concurrent.schedule

class AddEventActivity : AppCompatActivity() {

    private var _binding: ActivityAddEventBinding? = null
    private val binding get() = _binding
    private val requestCodeEvent = 200
    private var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)
        _binding = ActivityAddEventBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnSelectImage?.setOnClickListener {
            selectImage()
        }
        binding?.btnBack?.setOnClickListener {
            onBackPressed()
        }

        binding?.btnAddEvent?.setOnClickListener {
            Toast.makeText(this, "save event!", Toast.LENGTH_SHORT).show()
        }
    }

    fun selectImage()
    {
        var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, requestCodeEvent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == requestCodeEvent){
            Thread(Runnable {
                imageUri = data?.data
                Timer().schedule(900){
                    binding?.ivAddEvent?.post {
                        binding?.ivAddEvent?.setImageURI(imageUri)
                    }
                }
            }).start()
        }
    }
}