package com.example.deswita.ui.mainmenu.story

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.FragmentStoryBinding
import com.example.deswita.models.Story
import com.google.android.material.appbar.AppBarLayout

class StoryFragment : Fragment() {

    private var _binding: FragmentStoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var storyAdapter: StoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoryBinding.inflate(inflater,container,false)

        val findViewById = activity?.findViewById<AppBarLayout>(R.id.appBarLayoutMain)
        findViewById?.visibility = View.VISIBLE

        storyAdapter = StoryAdapter(requireActivity())
        initialRecycler()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userImage1 = BitmapFactory.decodeResource(resources, R.drawable.user_1)
        val postImage1 = BitmapFactory.decodeResource(resources,R.drawable.post_1)
        val userImage2 = BitmapFactory.decodeResource(resources, R.drawable.user_2)
        val postImage2 = BitmapFactory.decodeResource(resources,R.drawable.post_2)

        var storiesDummy = arrayListOf<Story>(
            Story(
                id = 1,
                name = "fiqri ardiansyah",
                description = "Jakarta ,indonesia",
                contentText = "Lorem Ipsum is simply...",
                profile = "user_1",
                commentTotal = 121,
                contentImage = "post_1",
                likeTotal = 234
            ),
            Story(
                id = 1,
                name = "jhon doe",
                description = "Medan ,indonesia",
                contentText = "Lorem Ipsum is simply dummy text of the printing",
                profile = "user_2",
                commentTotal = 324,
                contentImage = "post_2",
                likeTotal = 23432
            ),
            Story(
                id = 1,
                name = "jhon doe",
                description = "Medan ,indonesia",
                contentText = "Lorem Ipsum is simply dummy text of the printing",
                profile = "user_1",
                commentTotal = 324,
                contentImage = "post_2",
                likeTotal = 23432
            )
        )

        storyAdapter.setData(storiesDummy)
    }

    fun initialRecycler(){
        binding.rvStory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvStory.setHasFixedSize(true)
        binding.rvStory.adapter = storyAdapter
    }



}