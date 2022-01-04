package com.example.deswita.ui.mainmenu.event

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deswita.R
import com.example.deswita.databinding.FragmentEventBinding
import com.example.deswita.models.Event
import com.example.deswita.ui.MainViewModel
import com.google.android.material.appbar.AppBarLayout


class EventFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!
    private lateinit var eventAdapter: EventAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventBinding.inflate(inflater,container,false)

        val findViewById = activity?.findViewById<AppBarLayout>(R.id.appBarLayoutMain)
        findViewById?.visibility = View.VISIBLE

        eventAdapter = EventAdapter(requireActivity())
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialRecyclerView()
        setDate()

        val events = listOf(
            Event(1,"post_1","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(2,"post_2","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(3,"post_1","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(4,"post_2","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(1,"post_1","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(2,"post_2","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(3,"post_1","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            Event(4,"post_2","12 Desember 2021","Bali event festival","Bali,Indonesia","new open-source projects and other ways of contributing to the community. If you have used our component in your project we would be extremely happy"),
            )

        eventAdapter.setData(events)


        binding.btnEventCalendar.setOnClickListener(this)
        recyclerViewScrollListener()
    }

    private fun setDate() {
        mainViewModel.date.observe(requireActivity(),{
            binding.tvDateEvent.text = it
        })
    }

    private fun recyclerViewScrollListener() {
        binding.rvEvent.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                binding.dateEventLayout.elevation = if(dy > 0) 10f else 0f
            }
        })
    }

    private fun initialRecyclerView() {
        binding.rvEvent.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEvent.setHasFixedSize(true)
        binding.rvEvent.adapter = eventAdapter
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.btnEventCalendar.id -> {
                val calendarFragment = CalendarFragment()
                calendarFragment.show(childFragmentManager,CalendarFragment::class.java.simpleName)
            }
            binding.fabEvent.id ->{
                Log.i("FAB", "Fab di click")
                activity?.let {
                    val intent = Intent(it, AddEventActivity::class.java)
                    it.startActivity(intent)
                }
            }
        }
    }
}