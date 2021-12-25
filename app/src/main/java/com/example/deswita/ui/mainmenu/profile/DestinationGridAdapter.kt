package com.example.deswita.ui.mainmenu.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deswita.databinding.ItemEventGridLayoutBinding
import com.example.deswita.models.Destination

class DestinationGridAdapter : RecyclerView.Adapter<DestinationGridAdapter.ViewHolder>() {

    private var destinations = emptyList<Destination>()

    fun setData(destination: List<Destination>){
        this.destinations = destination
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemEventGridLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(destinations: Destination){
            binding.imageView.load(destinations.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEventGridLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(destinations[position])
    }

    override fun getItemCount(): Int = destinations.size
}