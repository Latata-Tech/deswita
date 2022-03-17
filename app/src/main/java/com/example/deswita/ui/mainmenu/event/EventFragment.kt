package com.example.deswita.ui.mainmenu.event

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import java.text.SimpleDateFormat
import java.util.*

class EventFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!
    private lateinit var eventAdapter: EventAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var date: String

    companion object {
        const val EXTRA_DATE = "extra_date"
    }

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

        if(savedInstanceState != null) {
            date = savedInstanceState.getString(EXTRA_DATE).toString()
            binding.tvDateEvent.text = date
        }else {
            setDate(Date())
        }

        eventAdapter.setData(mainViewModel.getEvent())
        binding.btnEventCalendar.setOnClickListener(this)
        binding.fabEvent.setOnClickListener(this)

        recyclerViewScrollListener()
    }

    private fun setDate(dt: Date) {
        val sdf = SimpleDateFormat("dd MMMM yyyy")
        val currentDate = sdf.format(dt)
        date = currentDate
        binding.tvDateEvent.text = date
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(EXTRA_DATE,date)
        super.onSaveInstanceState(outState)
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

                val bundle = Bundle()
                bundle.putString(EXTRA_DATE,date)
                calendarFragment.arguments = bundle

                calendarFragment.show(childFragmentManager,CalendarFragment::class.java.simpleName)
                calendarFragment.setOnClickItemCallback(object: CalendarFragment.OnClickItemCallback{
                    override fun onClick(dt: Date) {
                        setDate(dt)
                    }
                })
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