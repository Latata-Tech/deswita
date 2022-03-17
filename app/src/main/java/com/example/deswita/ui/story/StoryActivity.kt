package com.example.deswita.ui.story

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.deswita.R
import com.example.deswita.databinding.ActivityStoryBinding
import com.example.deswita.models.Destination
import com.example.deswita.models.Story
import com.example.deswita.ui.mainmenu.story.adapters.CommentAdapter
import com.example.deswita.utils.CapitalizeAllWord
import com.example.deswita.utils.CapitalizeFirstWord
import com.example.deswita.utils.Utils

class StoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoryBinding
    private var story: Story? = null
    private lateinit var commentAdapter: CommentAdapter

    companion object {
        const val EXTRA_STORY = "EXTRA_STORY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)
        binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        story = intent.getParcelableExtra<Story>(EXTRA_STORY) as Story
        commentAdapter = CommentAdapter(this)

        init()
        initRecyclerviewComment()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun init() {
        with(binding) {
            profileImage.load(Utils.getImageDrawable(this@StoryActivity,story!!.profile))
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

        if(story?.comments != null) {
            commentAdapter.setData(story!!.comments)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        story = null
    }

}