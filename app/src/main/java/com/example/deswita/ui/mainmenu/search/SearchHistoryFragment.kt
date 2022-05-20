package com.example.deswita.ui.mainmenu.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deswita.R
import com.example.deswita.databinding.FragmentSearchHistoryBinding
import com.example.deswita.models.SearchHistory
import com.google.android.material.appbar.AppBarLayout
import java.util.*
import kotlin.collections.ArrayList

class SearchHistoryFragment : Fragment() {
    private lateinit var _binding: FragmentSearchHistoryBinding
    private val binding get() =  _binding

    private lateinit var searhHistoryAdapter: SearchHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchHistoryBinding.inflate(inflater, container, false)

        searhHistoryAdapter = SearchHistoryAdapter()
        initRecyleView()

        val arg = arguments?.getStringArrayList(SearchActivity.HISTORY_LIST)
        if(!arg.isNullOrEmpty()) {
            setData(arg)
        }

        return binding.root
    }

    private fun initRecyleView() {
        binding.listSearchHistory.layoutManager = object : LinearLayoutManager(requireContext()){
            override fun canScrollVertically(): Boolean {
                return true
            }
        }
        binding.listSearchHistory.adapter = searhHistoryAdapter
    }

    private fun setData(histories: ArrayList<String>)
    {
        searhHistoryAdapter.setData(histories)
    }

}