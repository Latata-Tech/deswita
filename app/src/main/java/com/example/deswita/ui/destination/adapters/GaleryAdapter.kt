package com.example.deswita.ui.destination.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deswita.databinding.ItemEventGridLayoutBinding
import com.example.deswita.databinding.ItemGaleryLayoutBinding
import com.example.deswita.utils.Utils

class GaleryAdapter(private val context: Context): RecyclerView.Adapter<GaleryAdapter.ViewHolder>() {

    private var galeries = emptyList<String>()

    fun setData(galeries: List<String>) {
        this.galeries = galeries
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemGaleryLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(galery: String,position: Int) {
            with(binding) {
                imageView.load(Utils.getImageDrawable(context,galery))

                if(position == galeries.size - 1) {
                    val params = itemView.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(10,0,40,0)
                    itemView.layoutParams = params
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGaleryLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(galeries[position],position)
    }

    override fun getItemCount(): Int = galeries.size
}