package com.example.deswita.ui.mainmenu.profile

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.example.deswita.R
import com.example.deswita.adapter.ProfilePagerAdapter
import com.example.deswita.databinding.FragmentProfileBinding
import com.example.deswita.ui.mainmenu.home.HomeFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

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
    }

    private fun tab (){
        val sectionPagerAdapter = ProfilePagerAdapter(requireActivity())
        val viewPager = binding.viewPager
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