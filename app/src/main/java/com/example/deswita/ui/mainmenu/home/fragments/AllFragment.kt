package com.example.deswita.ui.mainmenu.home.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import coil.load
import com.example.deswita.R
import com.example.deswita.databinding.FragmentAllBinding
import com.example.deswita.models.Destination
import com.example.deswita.models.Event
import com.example.deswita.models.weatherResponse
import com.example.deswita.service.WeatherServiceNew
import com.example.deswita.ui.MainViewModel
import com.example.deswita.ui.destination.DestinationActivity
import com.example.deswita.ui.event.EventActivity
import com.example.deswita.ui.mainmenu.home.adapters.DestinationAdapter
import com.example.deswita.ui.mainmenu.home.adapters.TopDestinationAdapter
import com.example.deswita.ui.mainmenu.home.adapters.TopEventAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds


class AllFragment : Fragment() {

    private lateinit var _binding: FragmentAllBinding
    private val binding get() = _binding
    private lateinit var topDestinationAdapter: TopDestinationAdapter
    private lateinit var topUpcomingEventAdapter: TopEventAdapter
    private lateinit var topOngoingEventAdapter: TopEventAdapter
    private lateinit var destinationAdapter: DestinationAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllBinding.inflate(LayoutInflater.from(inflater.context),container,false)
        var sharedPref = this.activity?.getSharedPreferences("removeAds", Context.MODE_PRIVATE )
        val statusAds = sharedPref?.getBoolean("removeAds", false)
        if(statusAds == false){
            MobileAds.initialize(inflater.context)
            binding.adView.loadAd(AdRequest.Builder().build())
        }
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

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
        observeWeather()
    }

    private fun observeWeather() {
        mainViewModel.weather.observe(requireActivity()) { weather ->
            binding.progressGetWeather.visibility = View.GONE
            binding.constraintLayoutWeather.visibility = View.VISIBLE
            binding.tvWeatherLocation.text = weather.name
            binding.ivWeatherIcon.load("https://openweathermap.org/img/w/${weather.weather?.get(0)?.icon}.png")
            binding.tvTemp.text = "${weather.main?.temp?.minus(273)?.toInt()} C"
            binding.tvWeatherDescription.text = weather.weather?.get(0)?.main
        }
    }

    private fun initialRecyclerViewTopDestination() {
        binding.rvTopDestinations.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        binding.rvTopDestinations.setHasFixedSize(true)
        binding.rvTopDestinations.adapter = topDestinationAdapter

        topDestinationAdapter.setData(mainViewModel.destinationDummy1)

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

        topUpcomingEventAdapter.setData(mainViewModel.eventDummy1)
        topOngoingEventAdapter.setData(mainViewModel.eventDummy2)

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

        destinationAdapter.setData(mainViewModel.destinationDummy2)

        destinationAdapter.setOnClickItemCallback(object: DestinationAdapter.OnClickItemCallback {
            override fun onClick(destination: Destination) {
                val intent = Intent(requireContext(),DestinationActivity::class.java)
                intent.putExtra(DestinationActivity.EXTRA_DESTINATION,destination)
                startActivity(intent)
            }
        })

    }




}