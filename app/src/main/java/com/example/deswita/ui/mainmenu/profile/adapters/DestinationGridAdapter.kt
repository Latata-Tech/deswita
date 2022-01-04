package com.example.deswita.ui.mainmenu.profile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deswita.databinding.ItemEventGridLayoutBinding
import com.example.deswita.models.Destination
import com.example.deswita.utils.Utils

class DestinationGridAdapter(private val context: Context) : RecyclerView.Adapter<DestinationGridAdapter.ViewHolder>() {

    private var onClickItemCallback: OnClickItemCallback? = null

    fun setOnClickItemCallback(onClickItemCallback: OnClickItemCallback) {
        this.onClickItemCallback = onClickItemCallback
    }

    interface OnClickItemCallback {
        fun onClick(destination: Destination)
    }

    private var destinations = emptyList<Destination>()

    fun setData(destination: List<Destination>){
        this.destinations = destination
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemEventGridLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(destinations: Destination){
            binding.imageView.load(Utils.getImageDrawable(context,destinations.image))
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