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
import com.example.deswita.databinding.FragmentDestinationsBinding
import com.example.deswita.models.Destination
import com.example.deswita.ui.MainViewModel
import com.example.deswita.ui.destination.DestinationActivity
import com.example.deswita.ui.mainmenu.event.EventAdapter
import com.example.deswita.ui.mainmenu.profile.adapters.DestinationGridAdapter

class DestinationsFragment : Fragment() {

    private lateinit var _binding: FragmentDestinationsBinding
    private val binding get() = _binding
    private lateinit var destinationGridAdapter: DestinationGridAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDestinationsBinding.inflate(LayoutInflater.from(inflater.context),container,false)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

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

        destinationGridAdapter.setData(mainViewModel.destinationDummy3)
        destinationGridAdapter.setOnClickItemCallback(object: DestinationGridAdapter.OnClickItemCallback {
            override fun onClick(destination: Destination) {
                val intent = Intent(requireActivity(),DestinationActivity::class.java)
                intent.putExtra(DestinationActivity.EXTRA_DESTINATION,destination)
                startActivity(intent)
            }
        })

    }

}