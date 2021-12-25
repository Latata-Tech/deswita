package com.example.deswita.ui.mainmenu.profile.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.FragmentEventsBinding
import com.example.deswita.models.Event
import com.example.deswita.ui.mainmenu.profile.EventGridAdapter


class EventsFragment : Fragment() {

    private lateinit var _binding: FragmentEventsBinding
    private val binding get() = _binding
    private lateinit var eventGridAdapter: EventGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventsBinding.inflate(LayoutInflater.from(inflater.context),container,false)

        eventGridAdapter = EventGridAdapter()
        initRecyclerView()

        setData()

        return binding.root
    }

    private fun initRecyclerView() {
        binding.rvEvents.layoutManager = object: GridLayoutManager(requireContext(),3) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.rvEvents.setHasFixedSize(true)
        binding.rvEvents.adapter = eventGridAdapter
    }

    private fun setData(){
        val postImage1 = BitmapFactory.decodeResource(resources,R.drawable.post_1)
        val postImage2 = BitmapFactory.decodeResource(resources,R.drawable.post_2)
        val events = listOf(
            Event(1,postImage1,"12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(2,postImage2,"12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(3,postImage1,"12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(4,postImage2,"12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(1,postImage1,"12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(2,postImage2,"12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(3,postImage1,"12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(4,postImage2,"12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
        )

        eventGridAdapter.setData(events)

    }

}