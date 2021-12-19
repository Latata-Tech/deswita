package com.example.deswita.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.deswita.R

class AdapterSliderWelcome(var list: List<Int>, var titleTextList: List<String>, var detailTextList: List<String>,var context: Context) : PagerAdapter(){
    private lateinit var ImgList:List<Int>

    lateinit var layoutInflater: LayoutInflater
    lateinit var ctx: Context


    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        layoutInflater = LayoutInflater.from(context)
        /**
         * Set View to Inflate
         */
        val view = layoutInflater.inflate(R.layout.item_slider_welcome, container, false)

        /**
         * Get property from layout
         */
        val img = view.findViewById<ImageView>(R.id.imageSlider)
        val titleText = view.findViewById<TextView>(R.id.titleTextSlider)
        val detailText = view.findViewById<TextView>(R.id.detailTextSlider)

        /**
         * Set value to property layout
         */
        img.setImageResource(list[position])
        titleText.text = titleTextList[position]
        detailText.text = detailTextList[position]

        container.addView(view,0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}