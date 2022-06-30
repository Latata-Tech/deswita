package com.example.deswita.ui.story

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.deswita.R
import com.example.deswita.databinding.ActivityStoryBinding
import com.example.deswita.models.Destination
import com.example.deswita.models.Story
import com.example.deswita.ui.auth.RegisterActivity
import com.example.deswita.ui.mainmenu.story.AddStoryActivity
import com.example.deswita.ui.mainmenu.story.CommentStoryActivity
import com.example.deswita.ui.mainmenu.story.adapters.CommentAdapter
import com.example.deswita.ui.reviews.AddReviewActivity
import com.example.deswita.utils.CapitalizeAllWord
import com.example.deswita.utils.CapitalizeFirstWord
import com.example.deswita.utils.Storage
import com.example.deswita.utils.Utils

class StoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoryBinding
    private var story: Story? = null
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var storageDB: Storage

    companion object {
        const val EXTRA_STORY = "EXTRA_STORY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)
        binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storageDB = Storage

        story = intent.getParcelableExtra<Story>(EXTRA_STORY) as Story
        commentAdapter = CommentAdapter(this)

        init()
        initRecyclerviewComment()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.BtnAddComment.setOnClickListener{
            AddComment()

        }
    }

    private fun AddComment() {
//        val intent = Intent(this@StoryActivity,CommentStoryActivity::class.java)
//        startActivity(intent)
        startActivity(Intent(this@StoryActivity, CommentStoryActivity::class.java))
    }

    private fun init() {
        with(binding) {
            storageDB.getImage(story!!.profile) {url ->
                profileImage.load(url)
            }
            ivPost.load(story!!.contentImage)
            tvNamePost.text = story?.name?.CapitalizeAllWord()
            tvDescriptionPost.text = story?.description?.CapitalizeFirstWord()
            tvContentPost.text = story?.contentText?.CapitalizeFirstWord()
            tvLike.text = story?.likeTotal.toString()
        }
    }

    private fun initRecyclerviewComment() {
        binding.rvComments.layoutManager = LinearLayoutManager(this)
        binding.rvComments.setHasFixedSize(true)
        binding.rvComments.adapter = commentAdapter

//        if(story?.comments != null) {
//            commentAdapter.setData(story!!.comments)
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        story = null
    }

}
