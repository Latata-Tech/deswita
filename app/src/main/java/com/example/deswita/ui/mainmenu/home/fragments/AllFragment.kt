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
import com.example.deswita.databinding.FragmentAllBinding
import com.example.deswita.models.Destination
import com.example.deswita.models.Event
import com.example.deswita.ui.destination.DestinationActivity
import com.example.deswita.ui.event.EventActivity
import com.example.deswita.ui.mainmenu.home.adapters.DestinationAdapter
import com.example.deswita.ui.mainmenu.home.adapters.TopDestinationAdapter
import com.example.deswita.ui.mainmenu.home.adapters.TopEventAdapter


class AllFragment : Fragment() {

    private lateinit var _binding: FragmentAllBinding
    private val binding get() = _binding
    private lateinit var topDestinationAdapter: TopDestinationAdapter
    private lateinit var topUpcomingEventAdapter: TopEventAdapter
    private lateinit var topOngoingEventAdapter: TopEventAdapter
    private lateinit var destinationAdapter: DestinationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllBinding.inflate(LayoutInflater.from(inflater.context),container,false)

        topDestinationAdapter = TopDestinationAdapter(requireActivity())
        topUpcomingEventAdapter = TopEventAdapter(requireActivity())
        topOngoingEventAdapter = TopEventAdapter(requireActivity())
        destinationAdapter = DestinationAdapter(requireActivity())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialRecyclerViewTopDestination()
        initialRecyclerViewTopEvent()
        initialRecyclerViewDestinations()
    }

    private fun initialRecyclerViewTopDestination() {
        binding.rvTopDestinations.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        binding.rvTopDestinations.setHasFixedSize(true)
        binding.rvTopDestinations.adapter = topDestinationAdapter

        val postImage1 = BitmapFactory.decodeResource(resources,R.drawable.post_1)
        val postImage2 = BitmapFactory.decodeResource(resources,R.drawable.post_2)
        val destinations = listOf(
            Destination("post_1","Taman Mora Indah","Jl. Sisingamangaraja, Bangun Mulia, Kec. Medan Amplas, Kota Medan, Sumatera Utara.",false,5.0,34.4),
            Destination("post_2","Danau Toba","Bukit Barisan, Kabupaten Toba Samosir, Sumatera Utara.",false,3.5,2323.2),
            Destination("post_1","Menara Pandang Tele","Turpuk Limbong, Kec. Harian, Kab. Samosir, Sumatra Utara.",true,4.5,232.33),
            Destination("post_2","Geosite Sipinsur","Parulohan, Kec. Paranginan, Kab. Humbang Hasundutan, Sumatra Utara.",false,3.4,232.3),
            Destination("post_1","Danau Linting","Jl. Sinembah Tj. Kec. Muda Hulu, Kab. Deli Serdang, Sumatera Utara.",true,5.0,2.2),
        )


        topDestinationAdapter.setData(destinations)

        topDestinationAdapter.setOnClickItemCallback(object: TopDestinationAdapter.OnClickItemCallback {
            override fun onClick(destination: Destination) {
                val intent = Intent(requireContext(),DestinationActivity::class.java)
                intent.putExtra(DestinationActivity.EXTRA_DESTINATION,destination)
                startActivity(intent)
            }
        })

    }

    private fun initialRecyclerViewTopEvent() {
        binding.rvTopUpcomingEvent.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        binding.rvTopOngoingEvent.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        binding.rvTopOngoingEvent.setHasFixedSize(true)
        binding.rvTopUpcomingEvent.setHasFixedSize(true)
        binding.rvTopUpcomingEvent.adapter = topUpcomingEventAdapter
        binding.rvTopOngoingEvent.adapter = topOngoingEventAdapter


        val postImage1 = BitmapFactory.decodeResource(resources,R.drawable.post_1)
        val postImage2 = BitmapFactory.decodeResource(resources,R.drawable.post_2)
        val events = listOf(
            Event(1,"post_1","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(2,"post_2","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(3,"post_1","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(2,"post_2","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(3,"post_1","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(4,"post_2","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
        )

        topUpcomingEventAdapter.setData(events)
        topOngoingEventAdapter.setData(events)

        topOngoingEventAdapter.setOnClickItemCallback(object: TopEventAdapter.OnClickItemCallback {
            override fun onClick(event: Event) {
                val intent = Intent(requireContext(),EventActivity::class.java)
                intent.putExtra(EventActivity.EXTRA_EVENT,event)
                startActivity(intent)
            }
        })

        topUpcomingEventAdapter.setOnClickItemCallback(object: TopEventAdapter.OnClickItemCallback {
            override fun onClick(event: Event) {
                val intent = Intent(requireContext(),EventActivity::class.java)
                intent.putExtra(EventActivity.EXTRA_EVENT,event)
                startActivity(intent)
            }
        })

    }

    private fun initialRecyclerViewDestinations() {

        binding.rvDestinations.layoutManager = object: StaggeredGridLayoutManager(2,
            LinearLayoutManager.VERTICAL) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.rvDestinations.setHasFixedSize(true)
        binding.rvDestinations.adapter = destinationAdapter

        val postImage1 = BitmapFactory.decodeResource(resources,R.drawable.post_1)
        val postImage2 = BitmapFactory.decodeResource(resources,R.drawable.post_2)
        val destinations = listOf(
            Destination("post_1","Taman sdf","Jl. Sisingamangaraja, Bangun Mulia, Kec. Medan Amplas, Kota Medan, Sumatera Utara.",false,5.0,34.4),
            Destination("post_2","Danau","Bukit Barisan",false,3.5,2323.2),
            Destination("post_1","Menara","Turpuk Limbong, Kec. Harian",true,4.5,232.33),
            Destination("post_2","Geosite","Parulohan",false,3.4,232.3),
            Destination("post_1","Danau Linting","Jl. Sinembah Tj. Kec. Muda Hulu, Kab. Deli Serdang, Sumatera Utara.",true,5.0,2.2),
        )

        destinationAdapter.setData(destinations)

        destinationAdapter.setOnClickItemCallback(object: DestinationAdapter.OnClickItemCallback {
            override fun onClick(destination: Destination) {
                val intent = Intent(requireContext(),DestinationActivity::class.java)
                intent.putExtra(DestinationActivity.EXTRA_DESTINATION,destination)
                startActivity(intent)
            }
        })

    }




}