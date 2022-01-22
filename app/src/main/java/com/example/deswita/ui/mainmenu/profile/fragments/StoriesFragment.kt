package com.example.deswita.ui.mainmenu.profile.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.FragmentStoriesBinding
import com.example.deswita.models.Story
import com.example.deswita.ui.MainViewModel
import com.example.deswita.ui.mainmenu.profile.adapters.StoriesAdapter


class StoriesFragment : Fragment() {

    private lateinit var _binding: FragmentStoriesBinding
    private val binding get() = _binding
    private lateinit var storiesAdapter: StoriesAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoriesBinding.inflate(LayoutInflater.from(inflater.context),container,false)
        storiesAdapter = StoriesAdapter(requireActivity())
        initRecyclerView()

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

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
        storiesAdapter.setData(mainViewModel.storiesDummy2)

    }

}