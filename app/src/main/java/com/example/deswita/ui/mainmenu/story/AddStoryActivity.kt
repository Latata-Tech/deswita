package com.example.deswita.ui.mainmenu.story

import android.content.Context
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
import android.util.Log
import android.view.View
import com.example.deswita.models.Story
import com.example.deswita.utils.Firestore
import com.example.deswita.utils.Storage
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.example.deswita.utils.random
import com.google.firebase.Timestamp


class AddStoryActivity : AppCompatActivity() {

    private var _binding: ActivityAddStoryBinding? = null
    private val binding get() = _binding
    private lateinit var firestoreDB: FirebaseFirestore
    private lateinit var storageDB: Storage

    private val requestCodeStory = 200
    private var imageUri : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_story)
        _binding = ActivityAddStoryBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var sharedPref = getSharedPreferences("nama", Context.MODE_PRIVATE )

        firestoreDB = Firestore.db
        storageDB = Storage

        binding?.ivAddStory?.setOnClickListener {
            selectImage()
        }

        binding?.btnBack?.setOnClickListener {
            onBackPressed()
        }

        binding?.btnAddStory?.setOnClickListener {
            val title = binding?.edtTitleAddStory
            val location = binding?.edtLocationAddStory
            val description = binding?.edtDescriptionAddStory

            when {
                title?.text?.isEmpty() == true -> {
                    title.error = "Required"
                }
                location?.text?.isEmpty() == true -> {
                    location.error = "Required"
                }
                description?.text?.isEmpty() == true -> {
                    description.error = "Required"
                }
                imageUri == null -> {
                    Toast.makeText(this,"Image required",Toast.LENGTH_SHORT)
                }
                else -> {
                    binding?.loading?.visibility = View.VISIBLE
                    storageDB.uploadImage(imageUri!!) { uri ->

                        val id = (1..9999999).random()
                        val profile = "user_${(1..10).random()}.jpg"
                        val name = sharedPref?.getString("nama","").toString()

                        val story = Story(id,name,(description?.text ?: "").toString(),(location?.text ?: "").toString(),uri,profile,0,0,0 , Timestamp(Date()))
                        firestoreDB.collection("stories").add(story)
                            .addOnSuccessListener {
                                binding?.loading?.visibility = View.GONE
                                Toast.makeText(this,"Your story uploaded!",Toast.LENGTH_SHORT)
                                onBackPressed()
                            }
                    }
                }
            }
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