package com.example.deswita.ui.mainmenu.home.fragments

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.FragmentRatingBinding
import com.example.deswita.models.Destination
import com.example.deswita.ui.MainViewModel
import com.example.deswita.ui.destination.DestinationActivity
import com.example.deswita.ui.mainmenu.home.adapters.DestinationAdapter


class RatingFragment : Fragment() {

    private lateinit var _binding: FragmentRatingBinding
    private val binding get() = _binding
    private lateinit var adapter: DestinationAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRatingBinding.inflate(LayoutInflater.from(inflater.context),container,false)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        adapter = DestinationAdapter(requireActivity())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialRecyclerView()
    }

    private fun initialRecyclerView() {

        binding.rvDestination.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rvDestination.setHasFixedSize(true)
        binding.rvDestination.adapter = adapter

        adapter.setData(mainViewModel.destinationDummy1)

        adapter.setOnClickItemCallback(object: DestinationAdapter.OnClickItemCallback {
            override fun onClick(destination: Destination) {
                val intent = Intent(requireContext(), DestinationActivity::class.java)
                intent.putExtra(DestinationActivity.EXTRA_DESTINATION,destination)
                startActivity(intent)
            }
        })
    }

}