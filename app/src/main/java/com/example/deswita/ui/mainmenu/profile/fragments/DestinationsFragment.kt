package com.example.deswita.ui.mainmenu.profile.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.FragmentDestinationsBinding
import com.example.deswita.models.Destination
import com.example.deswita.ui.mainmenu.profile.adapters.DestinationGridAdapter

class DestinationsFragment : Fragment() {

    private lateinit var _binding: FragmentDestinationsBinding
    private val binding get() = _binding
    private lateinit var destinationGridAdapter: DestinationGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDestinationsBinding.inflate(LayoutInflater.from(inflater.context),container,false)
        destinationGridAdapter = DestinationGridAdapter(requireActivity())
        initRecyclerView()

        setData()
        return binding.root
    }

    private fun initRecyclerView() {
        binding.rvDestination.layoutManager = object: GridLayoutManager(requireContext(),3) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.rvDestination.setHasFixedSize(true)
        binding.rvDestination.adapter = destinationGridAdapter
    }

    private fun setData(){
        val postImage1 = BitmapFactory.decodeResource(resources,R.drawable.post_1)
        val postImage2 = BitmapFactory.decodeResource(resources,R.drawable.post_2)
        val destinations = listOf(
            Destination("post_1","","",false,4.5,121.3),
            Destination("post_2","","",false,4.5,232.toDouble()),
            Destination("post_2","","",false,3.4,121.3),
            Destination("post_2","","",false,4.4,121.3),
            Destination("post_1","","",false,4.5,121.3),
        )

        destinationGridAdapter.setData(destinations)

    }

}