package com.example.deswita.ui.mainmenu.profile.fragments

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.FragmentEventsBinding
import com.example.deswita.models.Event
import com.example.deswita.ui.MainViewModel
import com.example.deswita.ui.event.EventActivity
import com.example.deswita.ui.mainmenu.profile.adapters.EventGridAdapter


class EventsFragment : Fragment() {

    private lateinit var _binding: FragmentEventsBinding
    private val binding get() = _binding
    private lateinit var eventGridAdapter: EventGridAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventsBinding.inflate(LayoutInflater.from(inflater.context),container,false)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        eventGridAdapter = EventGridAdapter(requireActivity())
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

        eventGridAdapter.setData(mainViewModel.eventDummy2)

        eventGridAdapter.setOnClickItemCallback(object: EventGridAdapter.OnClickItemCallback{
            override fun onClick(event: Event) {
                val intent = Intent(requireContext(), EventActivity::class.java)
                intent.putExtra(EventActivity.EXTRA_EVENT,event)
                startActivity(intent)
            }
        })

    }

}