package com.example.deswita.ui.mainmenu.event

import android.content.Intent
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
import com.example.deswita.ui.event.EventActivity
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

        eventAdapter.setData(mainViewModel.getEvent())

        binding.btnEventCalendar.setOnClickListener(this)
        binding.fabEvent.setOnClickListener(this)

        recyclerViewScrollListener()
    }

    private fun setDate() {
        mainViewModel.date.observe(requireActivity(),{
            binding.tvDateEvent.text = it

            eventAdapter.setData(mainViewModel.getEvent())
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
        binding.rvEvent.recycledViewPool.clear()
        binding.rvEvent.adapter = eventAdapter

        eventAdapter.setOnClickItemCallback(object: EventAdapter.OnClickItemCallback{
            override fun onClick(event: Event) {
                val intent = Intent(requireContext(),EventActivity::class.java)
                intent.putExtra(EventActivity.EXTRA_EVENT,event)
                startActivity(intent)
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.btnEventCalendar.id -> {
                val calendarFragment = CalendarFragment()
                calendarFragment.show(childFragmentManager,CalendarFragment::class.java.simpleName)
            }
            binding.fabEvent.id ->{
                activity?.let {
                    val intent = Intent(it, AddEventActivity::class.java)
                    it.startActivity(intent)
                }
            }
        }
    }
}