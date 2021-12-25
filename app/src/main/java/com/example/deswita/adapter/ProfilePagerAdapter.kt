package com.example.deswita.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.deswita.ui.mainmenu.profile.fragments.DestinationsFragment
import com.example.deswita.ui.mainmenu.profile.fragments.EventsFragment
import com.example.deswita.ui.mainmenu.profile.fragments.StoriesFragment

class ProfilePagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = EventsFragment()
            1 -> fragment = DestinationsFragment()
            2 -> fragment = StoriesFragment()
        }

        return fragment as Fragment
    }


}