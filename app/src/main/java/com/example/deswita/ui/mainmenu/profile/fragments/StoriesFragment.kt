package com.example.deswita.ui.mainmenu.profile.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.FragmentStoriesBinding
import com.example.deswita.models.Destination
import com.example.deswita.models.Story
import com.example.deswita.ui.mainmenu.profile.EventGridAdapter
import com.example.deswita.ui.mainmenu.profile.StoriesAdapter


class StoriesFragment : Fragment() {

    private lateinit var _binding: FragmentStoriesBinding
    private val binding get() = _binding
    private lateinit var storiesAdapter: StoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoriesBinding.inflate(LayoutInflater.from(inflater.context),container,false)
        storiesAdapter = StoriesAdapter()
        initRecyclerView()

        setData()
        return binding.root
    }

    private fun initRecyclerView() {
        binding.rvStories.layoutManager = object: LinearLayoutManager(requireContext()) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.rvStories.setHasFixedSize(true)
        binding.rvStories.adapter = storiesAdapter
    }

    private fun setData(){
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
                profile = userImage1,
                commentTotal = 121,
                contentImage = postImage1,
                likeTotal = 234
            ),
            Story(
                id = 1,
                name = "fiqri ardiansyah",
                description = "Jakarta ,indonesia",
                contentText = "Lorem Ipsum is simply...",
                profile = userImage1,
                commentTotal = 121,
                contentImage = postImage1,
                likeTotal = 234
            ),
            Story(
                id = 1,
                name = "fiqri ardiansyah",
                description = "Jakarta ,indonesia",
                contentText = "Lorem Ipsum is simply...",
                profile = userImage1,
                commentTotal = 121,
                contentImage = postImage1,
                likeTotal = 234
            ),
            Story(
                id = 1,
                name = "fiqri ardiansyah",
                description = "Jakarta ,indonesia",
                contentText = "Lorem Ipsum is simply...",
                profile = userImage1,
                commentTotal = 121,
                contentImage = postImage1,
                likeTotal = 234
            ),
        )

        storiesAdapter.setData(storiesDummy)

    }

}