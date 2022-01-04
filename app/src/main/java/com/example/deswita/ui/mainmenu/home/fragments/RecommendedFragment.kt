package com.example.deswita.ui.mainmenu.home.fragments

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.FragmentRecommendedBinding
import com.example.deswita.models.Destination
import com.example.deswita.ui.destination.DestinationActivity
import com.example.deswita.ui.mainmenu.home.adapters.DestinationAdapter

class RecommendedFragment : Fragment() {

    private lateinit var _binding: FragmentRecommendedBinding
    private val binding get() = _binding
    private lateinit var adapter: DestinationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecommendedBinding.inflate(LayoutInflater.from(inflater.context),container,false)
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

        val postImage1 = BitmapFactory.decodeResource(resources,R.drawable.post_1)
        val postImage2 = BitmapFactory.decodeResource(resources,R.drawable.post_2)
        val destinations = listOf(
            Destination("post_1","Taman sdf","Jl. Sisingamangaraja, Bangun Mulia, Kec. Medan Amplas, Kota Medan, Sumatera Utara.",false,5.0,34.4),
            Destination("post_2","Danau","Bukit Barisan",false,3.5,2323.2),
            Destination("post_1","Menara","Turpuk Limbong, Kec. Harian",true,4.5,232.33),
            Destination("post_2","Geosite","Parulohan",false,3.4,232.3),
            Destination("post_1","Danau Linting","Jl. Sinembah Tj. Kec. Muda Hulu, Kab. Deli Serdang, Sumatera Utara.",true,5.0,2.2),
        )

        adapter.setData(destinations)

        adapter.setOnClickItemCallback(object: DestinationAdapter.OnClickItemCallback {
            override fun onClick(destination: Destination) {
                val intent = Intent(requireContext(), DestinationActivity::class.java)
                intent.putExtra(DestinationActivity.EXTRA_DESTINATION,destination)
                startActivity(intent)
            }
        })
    }


}