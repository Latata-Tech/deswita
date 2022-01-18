package com.example.deswita.ui.mainmenu.profile.fragments

import android.content.Intent
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
import com.example.deswita.ui.destination.DestinationActivity
import com.example.deswita.ui.mainmenu.event.EventAdapter
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
            Destination("post_1","Taman sdf","Jl. Sisingamangaraja, Bangun Mulia, Kec. Medan Amplas, Kota Medan, Sumatera Utara.",false,5.0,34.4),
            Destination("post_2","Danau","Bukit Barisan",false,3.5,2323.2),
            Destination("post_1","Menara","Turpuk Limbong, Kec. Harian",true,4.5,232.33),
            Destination("post_2","Geosite","Parulohan",false,3.4,232.3),
            Destination("post_1","Danau Linting","Jl. Sinembah Tj. Kec. Muda Hulu, Kab. Deli Serdang, Sumatera Utara.",true,5.0,2.2),
        )

        destinationGridAdapter.setData(destinations)
        destinationGridAdapter.setOnClickItemCallback(object: DestinationGridAdapter.OnClickItemCallback {
            override fun onClick(destination: Destination) {
                val intent = Intent(requireActivity(),DestinationActivity::class.java)
                intent.putExtra(DestinationActivity.EXTRA_DESTINATION,destination)
                startActivity(intent)
            }
        })

    }

}