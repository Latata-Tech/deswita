package com.example.deswita.ui.mainmenu.home

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.FragmentHomeBinding
import com.example.deswita.models.Destination
import com.example.deswita.models.Event
import com.example.deswita.ui.mainmenu.home.adapters.DestinationAdapter
import com.example.deswita.ui.mainmenu.home.adapters.TopDestinationAdapter
import com.example.deswita.ui.mainmenu.home.adapters.TopEventAdapter
import com.example.deswita.ui.mainmenu.home.fragments.*
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.chip.Chip


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var chipsFilter: List<Chip>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        val findViewById = activity?.findViewById<AppBarLayout>(R.id.appBarLayoutMain)
        findViewById?.visibility = View.VISIBLE

        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chipsFilter = homeViewModel.filterIds.map {  view.findViewById<Chip>(it) }

        chipFilterClickHandler()

        homeViewModel.activeFilter.observe(requireActivity(), {

            chipFilterChangeHandler(it)
            fragmentChangeHandler(it)

        })

    }

    private fun fragmentChangeHandler(chipId: Int) {

        var fragment: Fragment = AllFragment()

        when(chipId) {
            R.id.chip_filter_all -> fragment = AllFragment()
            R.id.chip_filter_recommended -> fragment = RecommendedFragment()
            R.id.chip_filter_popular -> fragment = PopularFragment()
            R.id.chip_filter_rating -> fragment = RatingFragment()
            R.id.chip_filter_favorite -> fragment = FavoriteFragment()
        }

        if(isAdded) {
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.containerFragment,fragment,fragment::class.java.simpleName).commit()
        }


    }

    private fun chipFilterChangeHandler(chipId: Int) {
        binding.chipGroup.clearCheck()
        chipsFilter.forEach { chip ->
            chip.isChecked = chip.id == chipId
        }
    }

    private fun chipFilterClickHandler() {
        chipsFilter.forEach { chip ->
            chip.setOnClickListener {
                homeViewModel.setActiveFilter( chip.id )
            }
        }
    }


}