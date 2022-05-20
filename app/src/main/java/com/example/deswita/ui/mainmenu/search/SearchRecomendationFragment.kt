package com.example.deswita.ui.mainmenu.search

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.FragmentSearchRecomendationBinding
import com.example.deswita.models.SearchRecomendation
import com.example.deswita.utils.SharePrefHelper

class SearchRecomendationFragment : Fragment() {
    private lateinit var _binding : FragmentSearchRecomendationBinding
    private val binding get() = _binding

    private lateinit var searchRecomendationAdapter: SearchRecomendationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchRecomendationBinding.inflate(inflater, container, false)
        searchRecomendationAdapter = SearchRecomendationAdapter()
        initRecyleView()
        setData()

        return binding.root
    }

    private fun initRecyleView(){
        binding.rvSearchRecomendationDestination.layoutManager = object : LinearLayoutManager(requireContext()){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.rvSearchRecomendationDestination.setHasFixedSize(true)
        binding.rvSearchRecomendationDestination.adapter = searchRecomendationAdapter
    }

    private fun setData(){
        val postImage1 = BitmapFactory.decodeResource(resources, R.drawable.post_1)
        val postImage2 = BitmapFactory.decodeResource(resources, R.drawable.post_2)

        val searchRecomendations = listOf<SearchRecomendation>(
            SearchRecomendation(
                id = 1,
                name = "Pantai Sejarah",
                location = "Batu - Bara, Sumut",
                image = postImage1,
                distance = listOf<Int>(31, 32)
            ),
            SearchRecomendation(
                id = 2,
                name = "Pantai Medan",
                location = "Batu - Bara, Sumut",
                image = postImage2,
                distance = listOf<Int>(31, 32)
            )
        )
        searchRecomendationAdapter.setData(searchRecomendations)
    }

}