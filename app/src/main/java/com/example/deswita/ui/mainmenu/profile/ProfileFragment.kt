package com.example.deswita.ui.mainmenu.profile

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.deswita.R
import com.example.deswita.adapter.ProfilePagerAdapter
import com.example.deswita.databinding.FragmentProfileBinding
import com.example.deswita.ui.destination.AddDestinationActivity
import com.example.deswita.ui.mainmenu.event.AddEventActivity
import com.example.deswita.ui.mainmenu.home.HomeFragment
import com.example.deswita.ui.mainmenu.search.SearchActivity
import com.example.deswita.ui.mainmenu.story.AddStoryActivity
import com.example.deswita.ui.notification.NotificationActivity
import com.example.deswita.ui.notification.NotificationAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager: ViewPager2

    companion object {
        val TAB_TEXT = arrayOf("Events","Destination","Stories")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val findViewById = activity?.findViewById<AppBarLayout>(R.id.appBarLayoutMain)
        findViewById?.visibility = View.GONE

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tab()
        scrollviewListener()


        binding.btnAppBarSearch.setOnClickListener{
            val intent = Intent(requireActivity(), SearchActivity::class.java)
            startActivity(intent)
        }

        binding.btnAppBarNotification.setOnClickListener {
            val intent = Intent(requireActivity(), NotificationActivity::class.java)
            startActivity(intent)
        }

        binding.fabProfile.setOnClickListener {
            when(viewPager.currentItem) {
                0 -> {
                    startActivity(Intent(requireContext(),AddEventActivity::class.java))
                }
                1 -> {
                    startActivity(Intent(requireContext(),AddDestinationActivity::class.java))
                }
                2 -> {
                    startActivity(Intent(requireContext(), AddStoryActivity::class.java))
                }
            }
        }

    }

    private fun tab (){
        val sectionPagerAdapter = ProfilePagerAdapter(requireActivity())
        viewPager = binding.viewPager
        viewPager.adapter = sectionPagerAdapter

        val tabs: TabLayout = binding.tabLayout
        TabLayoutMediator(tabs,viewPager) { tab, position ->
            tab.text = TAB_TEXT[position]
        }.attach()

    }

    private fun scrollviewListener() {
        binding.nestedScrollView.setOnScrollChangeListener(object: NestedScrollView.OnScrollChangeListener{
            override fun onScrollChange(
                v: NestedScrollView?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                if(scrollY >= 50) {
                    binding.appBarLayout.setBackgroundColor(Color.WHITE)
                    binding.btnAppBarSetting.setColorFilter(context!!.getColor(R.color.blue_dark))
                    binding.btnAppBarNotification.setColorFilter(context!!.getColor(R.color.blue_dark))
                    binding.btnAppBarSearch.setColorFilter(context!!.getColor(R.color.blue_dark))
                }else {
                    binding.appBarLayout.setBackgroundColor(context!!.getColor(R.color.transparent_500))
                    binding.btnAppBarSetting.setColorFilter(Color.WHITE)
                    binding.btnAppBarNotification.setColorFilter(Color.WHITE)
                    binding.btnAppBarSearch.setColorFilter(Color.WHITE)
                }
            }
        })
    }


}